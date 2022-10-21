package com.juanduzac.vendorlust.data.repository

import com.juanduzac.vendorlust.data.local.VendorDatabase
import com.juanduzac.vendorlust.data.mappers.toContactInfoEntity
import com.juanduzac.vendorlust.data.mappers.toGalleryItemEntity
import com.juanduzac.vendorlust.data.mappers.toImageEntity
import com.juanduzac.vendorlust.data.mappers.toOpeningHoursInDayEntity
import com.juanduzac.vendorlust.data.mappers.toVendor
import com.juanduzac.vendorlust.data.mappers.toVendorEntity
import com.juanduzac.vendorlust.data.remote.api.VendorsApi
import com.juanduzac.vendorlust.data.remote.dtos.OpeningHoursInWeekDto
import com.juanduzac.vendorlust.data.remote.dtos.VendorDto
import com.juanduzac.vendorlust.domain.model.VendorsResponse
import com.juanduzac.vendorlust.domain.repository.VendorsRepository
import com.juanduzac.vendorlust.domain.util.Resource
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

@Singleton
class VendorsRepositoryImpl @Inject constructor(
    private val api: VendorsApi,
    private val db: VendorDatabase
) : VendorsRepository {

    private val vendorDao = db.vendorDao

    override suspend fun getVendors(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<VendorsResponse>> {
        return flow {
            emit(Resource.Loading<VendorsResponse>(true))

            val localVendorsEntities = vendorDao.searchVendorsWithOpeningHoursAndHeroImage(query)
            val vendorsModel = localVendorsEntities.map { it.toVendor() }

            emit(
                Resource.Success(
                    data = VendorsResponse(vendors = vendorsModel)
                )
            )

            val isDbEmpty = localVendorsEntities.isEmpty() && query.isBlank()
            val shouldLoadFromCache = !isDbEmpty && !fetchFromRemote

            if (shouldLoadFromCache) {
                emit(Resource.Loading<VendorsResponse>(false))
                return@flow
            }

            val remoteVendorsDto = fetchVendorsFromRemote(this)

            remoteVendorsDto?.let { vendorsDto ->
                vendorDao.clearVendors()
                insertVendorsInCascade(vendorsDto)
                val vendorsEntities = vendorsDto.map { it.toVendorEntity() }
                vendorsEntities.let { entities ->
                    vendorDao.insertVendors(entities)
                }
                // Single source of truth, always coming from cache
                val localVendors = vendorDao.searchVendorsWithOpeningHoursAndHeroImage("")
                val response = VendorsResponse(localVendors.map { it.toVendor() })

                emit(Resource.Success<VendorsResponse>(response))
                emit(Resource.Loading<VendorsResponse>(false))
            }
        } as Flow<Resource<VendorsResponse>>
    }

    private suspend fun insertVendorsInCascade(vendorDtos: List<VendorDto>) {
        vendorDtos.forEach { vendorDto ->
            with(db) {

                vendorDto.contactInfoDto?.let { contactInfoDto ->
                    contactInfoDao.insertContactInfo(contactInfoDto.toContactInfoEntity(vendorDto.id))
                }

                vendorDto.galleryDto?.let { galleryItemDtos ->
                    galleryItemDtos.forEach { galleryItemDto ->

                        galleryItemDto.imageDto?.let { imageDto ->
                            imageDao.insertImage(
                                imageDto.toImageEntity(
                                    galleryItemId = galleryItemDto.id
                                )
                            )
                        }

                        galleryItemDao.insertGalleryItem(
                            galleryItemDto.toGalleryItemEntity(
                                vendorDto.id
                            )
                        )
                    }
                }

                vendorDto.heroImageDto?.let { imageDto ->
                    imageDao.insertImage(imageDto.toImageEntity(vendorId = vendorDto.id))
                }

                vendorDto.openingHoursDto?.let { openingHoursInWeekDto ->
                    insertOpeningHours(openingHoursInWeekDto)
                }

                vendorDao.insertVendor(vendorDto.toVendorEntity())

            }
        }
    }

    private suspend fun insertOpeningHours(openingHoursInWeekDto: OpeningHoursInWeekDto) {
        openingHoursInWeekDto.monday?.forEach { openingHoursInDayDto ->
            db.openingHoursInDayDao.insertOpeningHoursInDay(
                openingHoursInDayDto.toOpeningHoursInDayEntity("monday", openingHoursInWeekDto.id)
            )
        }

        openingHoursInWeekDto.thursday?.forEach { openingHoursInDayDto ->
            db.openingHoursInDayDao.insertOpeningHoursInDay(
                openingHoursInDayDto.toOpeningHoursInDayEntity("thursday", openingHoursInWeekDto.id)
            )
        }

        openingHoursInWeekDto.wednesday?.forEach { openingHoursInDayDto ->
            db.openingHoursInDayDao.insertOpeningHoursInDay(
                openingHoursInDayDto.toOpeningHoursInDayEntity(
                    "wednesday",
                    openingHoursInWeekDto.id
                )
            )
        }

        openingHoursInWeekDto.tuesday?.forEach { openingHoursInDayDto ->
            db.openingHoursInDayDao.insertOpeningHoursInDay(
                openingHoursInDayDto.toOpeningHoursInDayEntity("tuesday", openingHoursInWeekDto.id)
            )
        }

        openingHoursInWeekDto.friday?.forEach { openingHoursInDayDto ->
            db.openingHoursInDayDao.insertOpeningHoursInDay(
                openingHoursInDayDto.toOpeningHoursInDayEntity("friday", openingHoursInWeekDto.id)
            )
        }

        openingHoursInWeekDto.saturday?.forEach { openingHoursInDayDto ->
            db.openingHoursInDayDao.insertOpeningHoursInDay(
                openingHoursInDayDto.toOpeningHoursInDayEntity("saturday", openingHoursInWeekDto.id)
            )
        }

        openingHoursInWeekDto.sunday?.forEach { openingHoursInDayDto ->
            db.openingHoursInDayDao.insertOpeningHoursInDay(
                openingHoursInDayDto.toOpeningHoursInDayEntity("sunday", openingHoursInWeekDto.id)
            )
        }
    }

    private suspend fun fetchVendorsFromRemote(flowCollector: FlowCollector<Any>): List<VendorDto>? {
        return try {
            api.getVendors()
        } catch (e: IOException) {
            e.printStackTrace()
            flowCollector.emit(Resource.Error<VendorsResponse>("IO error, couldn't load data"))
            null
        } catch (e: HttpException) {
            e.printStackTrace()
            flowCollector.emit(Resource.Error<VendorsResponse>("Http error, couldn't load data"))
            null
        }
    }

}
