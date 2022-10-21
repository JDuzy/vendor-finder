package com.juanduzac.vendorlust.data.repository

import com.juanduzac.vendorlust.data.local.VendorDatabase
import com.juanduzac.vendorlust.data.mappers.toVendor
import com.juanduzac.vendorlust.data.mappers.toVendorEntity
import com.juanduzac.vendorlust.data.remote.api.VendorsApi
import com.juanduzac.vendorlust.domain.model.VendorsResponse
import com.juanduzac.vendorlust.domain.repository.VendorsRepository
import com.juanduzac.vendorlust.domain.util.Resource
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

@Singleton
class VendorsRepositoryImpl @Inject constructor(
    private val api: VendorsApi,
    private val db: VendorDatabase
) : VendorsRepository {

    private val vendorsDao = db.vendorDao

    override suspend fun getVendors(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<VendorsResponse>> {
        return flow {
            emit(Resource.Loading(true))

            val localVendorsEntities = vendorsDao.searchVendorsWithOpeningHoursAndHeroImage(query)
            val vendorsModel = localVendorsEntities.map { it.toVendor() }

            emit(
                Resource.Success(
                    data = VendorsResponse(vendors = vendorsModel)
                )
            )

            val isDbEmpty = localVendorsEntities.isEmpty() && query.isBlank()
            val shouldLoadFromCache = !isDbEmpty && !fetchFromRemote

            if (shouldLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteVendorResponseDto = try {
                api.getVendors()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("IO error, couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Http error, couldn't load data"))
                null
            }

            remoteVendorResponseDto?.let { vendorResponseDto ->
                vendorsDao.clearVendors()
                val vendorsEntities = vendorResponseDto.vendors?.map { it.toVendorEntity() }
                vendorsEntities?.let { entities ->
                    vendorsDao.insertVendors(entities)
                }
                // Single source of truth, always coming from cache
                val localVendors = vendorsDao.searchVendorsWithOpeningHoursAndHeroImage("")
                val response = VendorsResponse(localVendors.map { it.toVendor() })

                emit(Resource.Success(response))
                emit(Resource.Loading<VendorsResponse>(false))
            }
        }
    }
}
