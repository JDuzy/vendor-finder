package com.juanduzac.vendorlust.domain.repository

import com.juanduzac.vendorlust.domain.model.Vendor
import com.juanduzac.vendorlust.domain.model.VendorsResponse
import com.juanduzac.vendorlust.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface VendorsRepository {

    suspend fun getVendors(
        forceFetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<VendorsResponse>>

    suspend fun getVendorDetail(
        vendorId: Long
    ): Flow<Resource<Vendor>>
}
