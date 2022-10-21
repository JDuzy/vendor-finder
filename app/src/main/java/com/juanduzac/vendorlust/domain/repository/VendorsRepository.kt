package com.juanduzac.vendorlust.domain.repository

import com.juanduzac.vendorlust.domain.model.VendorsResponse
import com.juanduzac.vendorlust.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface VendorsRepository {

    suspend fun getVendors(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<VendorsResponse>>
}
