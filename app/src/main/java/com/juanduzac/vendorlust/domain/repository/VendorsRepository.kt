package com.juanduzac.vendorlust.domain.repository

import com.juanduzac.vendorlust.domain.model.VendorsResponse
import com.juanduzac.vendorlust.domain.util.Resource

interface VendorsRepository {

    suspend fun getVendors(): Resource<VendorsResponse>
}