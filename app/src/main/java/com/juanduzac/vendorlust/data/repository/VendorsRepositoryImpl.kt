package com.juanduzac.vendorlust.data.repository

import com.juanduzac.vendorlust.data.mappers.toVendorsResponse
import com.juanduzac.vendorlust.data.remote.api.VendorsApi
import com.juanduzac.vendorlust.domain.model.VendorsResponse
import com.juanduzac.vendorlust.domain.repository.VendorsRepository
import com.juanduzac.vendorlust.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class VendorsRepositoryImpl @Inject constructor(
    private val api: VendorsApi
) : VendorsRepository {

    override suspend fun getVendors(): Resource<VendorsResponse> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                Resource.Success(
                    data = api.getVendors().toVendorsResponse()
                )
            } catch (e: Exception) {
                e.printStackTrace()
                Resource.Error(e.message ?: ERROR_MSG)
            }
        }


    companion object {
        private const val ERROR_MSG = "An unknown error has occurred"
    }
}

