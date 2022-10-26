package com.juanduzac.vendorlust.data.repository

import com.juanduzac.vendorlust.data.local.VendorDatabase
import com.juanduzac.vendorlust.data.mappers.toContactInfoEntity
import com.juanduzac.vendorlust.data.mappers.toEntity
import com.juanduzac.vendorlust.data.mappers.toGalleryItemEntity
import com.juanduzac.vendorlust.data.mappers.toImageEntity
import com.juanduzac.vendorlust.data.mappers.toOpeningHoursInDayEntity
import com.juanduzac.vendorlust.data.mappers.toVendor
import com.juanduzac.vendorlust.data.mappers.toVendorEntity
import com.juanduzac.vendorlust.data.remote.api.VendorsApi
import com.juanduzac.vendorlust.data.remote.dtos.OpeningHoursInWeekDto
import com.juanduzac.vendorlust.data.remote.dtos.VendorDto
import com.juanduzac.vendorlust.domain.model.Day.FRIDAY
import com.juanduzac.vendorlust.domain.model.Day.MONDAY
import com.juanduzac.vendorlust.domain.model.Day.SATURDAY
import com.juanduzac.vendorlust.domain.model.Day.SUNDAY
import com.juanduzac.vendorlust.domain.model.Day.THURSDAY
import com.juanduzac.vendorlust.domain.model.Day.TUESDAY
import com.juanduzac.vendorlust.domain.model.Day.WEDNESDAY
import com.juanduzac.vendorlust.domain.model.Vendor
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
        forceFetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<VendorsResponse>> {
        return flow {
            emit(Resource.Loading<VendorsResponse>(true))

            if (!forceFetchFromRemote) {

                val localVendorsEntities =
                    vendorDao.searchVendorsWithOpeningHoursAndHeroImage(query)

                val isDbEmpty = localVendorsEntities.isEmpty() && query.isBlank()
                val shouldLoadFromCache = !isDbEmpty

                if (shouldLoadFromCache) {
                    val vendorsModel = localVendorsEntities.map { it.toVendor() }
                    emit(
                        Resource.Success(
                            data = VendorsResponse(vendors = vendorsModel)
                        )
                    )
                    emit(Resource.Loading<VendorsResponse>(false))
                    return@flow
                }
            }

            val remoteVendorsDto = fetchVendorsFromRemote(this)

            remoteVendorsDto?.let { vendorsDto ->

                val response = VendorsResponse(vendorsDto.map { it.toVendor() })
                emit(Resource.Success<VendorsResponse>(response))
                emit(Resource.Loading<VendorsResponse>(false))

                insertVendorsInCascade(vendorsDto)
            } ?: emit(Resource.Error<VendorsResponse>("Vendors not found"))
        } as Flow<Resource<VendorsResponse>>
    }

    override suspend fun getVendorDetail(vendorId: Long): Flow<Resource<Vendor>> {
        return flow {
            emit(Resource.Loading(true))
            val vendorEntity = vendorDao.getVendorDetails(vendorId)
            val vendor = vendorEntity?.toVendor()
            emit(
                Resource.Success(
                    data = vendor
                )
            )
            emit(Resource.Loading(false))
        }
    }

    private suspend fun insertVendorsInCascade(vendorDtos: List<VendorDto>) {
        vendorDtos.forEach { vndrDto ->

            db.vendorDao.insertVendor(vndrDto.toVendorEntity())

            vndrDto.contactInfoDto?.let { contactInfoDto ->
                db.contactInfoDao.insertContactInfo(
                    contactInfoDto.toContactInfoEntity(vndrDto.id)
                )
            }

            vndrDto.galleryDto?.let { galleryItemDtos ->
                galleryItemDtos.forEach { galleryItemDto ->

                    galleryItemDto.imageDto?.let { imageDto ->
                        db.imageDao.insertImage(
                            imageDto.toImageEntity(
                                galleryItemId = galleryItemDto.id
                            )
                        )
                    }

                    db.galleryItemDao.insertGalleryItem(
                        galleryItemDto.toGalleryItemEntity(
                            vndrDto.id
                        )
                    )
                }
            }

            vndrDto.heroImageDto?.let { imageDto ->
                db.imageDao.insertImage(imageDto.toImageEntity(vendorId = vndrDto.id))
            }

            vndrDto.openingHoursDto?.let { openingHoursInWeekDto ->
                insertOpeningHours(openingHoursInWeekDto, vndrDto.id)
            }
        }
    }

    private suspend fun insertOpeningHours(
        openingHoursInWeekDto: OpeningHoursInWeekDto,
        vendorId: Long
    ) {

        db.openingHoursInWeekDao.insertOpeningHoursInWeek(
            openingHoursInWeekDto.toEntity(vendorId)
        )

        openingHoursInWeekDto.monday?.forEach { openingHoursInDayDto ->
            db.openingHoursInDayDao.insertOpeningHoursInDay(
                openingHoursInDayDto.toOpeningHoursInDayEntity(
                    MONDAY,
                    openingHoursInWeekDto.id
                )
            )
        }

        openingHoursInWeekDto.thursday?.forEach { openingHoursInDayDto ->
            db.openingHoursInDayDao.insertOpeningHoursInDay(
                openingHoursInDayDto.toOpeningHoursInDayEntity(
                    THURSDAY,
                    openingHoursInWeekDto.id
                )
            )
        }

        openingHoursInWeekDto.wednesday?.forEach { openingHoursInDayDto ->
            db.openingHoursInDayDao.insertOpeningHoursInDay(
                openingHoursInDayDto.toOpeningHoursInDayEntity(
                    WEDNESDAY,
                    openingHoursInWeekDto.id
                )
            )
        }

        openingHoursInWeekDto.tuesday?.forEach { openingHoursInDayDto ->
            db.openingHoursInDayDao.insertOpeningHoursInDay(
                openingHoursInDayDto.toOpeningHoursInDayEntity(
                    TUESDAY,
                    openingHoursInWeekDto.id
                )
            )
        }

        openingHoursInWeekDto.friday?.forEach { openingHoursInDayDto ->
            db.openingHoursInDayDao.insertOpeningHoursInDay(
                openingHoursInDayDto.toOpeningHoursInDayEntity(
                    FRIDAY,
                    openingHoursInWeekDto.id
                )
            )
        }

        openingHoursInWeekDto.saturday?.forEach { openingHoursInDayDto ->
            db.openingHoursInDayDao.insertOpeningHoursInDay(
                openingHoursInDayDto.toOpeningHoursInDayEntity(
                    SATURDAY,
                    openingHoursInWeekDto.id
                )
            )
        }

        openingHoursInWeekDto.sunday?.forEach { openingHoursInDayDto ->
            db.openingHoursInDayDao.insertOpeningHoursInDay(
                openingHoursInDayDto.toOpeningHoursInDayEntity(
                    SUNDAY,
                    openingHoursInWeekDto.id
                )
            )
        }
    }

    private suspend fun fetchVendorsFromRemote(
        flowCollector: FlowCollector<Any>
    ): List<VendorDto>? {
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
