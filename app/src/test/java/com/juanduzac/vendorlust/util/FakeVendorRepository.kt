package com.juanduzac.vendorlust.util

import com.juanduzac.vendorlust.domain.model.Vendor
import com.juanduzac.vendorlust.domain.model.VendorsResponse
import com.juanduzac.vendorlust.domain.repository.VendorsRepository
import com.juanduzac.vendorlust.domain.util.Resource
import com.juanduzac.vendorlust.presentation.util.vendorExample
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeVendorRepository(): VendorsRepository {

    var shouldReturnNetworkError = false

    override suspend fun getVendors(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<VendorsResponse>> {
        return flow {
            emit(Resource.Loading<VendorsResponse>(isLoading = true))
            if (shouldReturnNetworkError)
                emit(Resource.Error<VendorsResponse>("Unknown error"))
            else
                emit(Resource.Success(data = VendorsResponse(listOf(vendorExample))))
            emit(Resource.Loading<VendorsResponse>(isLoading = false))
        }
    }

    override suspend fun getVendorDetail(vendorId: Long): Flow<Resource<Vendor>> {
        return flow {
            emit(Resource.Loading<Vendor>(isLoading = true))
            if (shouldReturnNetworkError)
                emit(Resource.Error<Vendor>("Unknown error"))
            else
                emit(Resource.Success(data = vendorExample))
            emit(Resource.Loading<Vendor>(isLoading = false))
        }
    }


}