package com.juanduzac.vendorlust.data.remote.dtos

import com.juanduzac.vendorlust.domain.model.Vendor

data class VendorsResponseDto(
    val vendors: List<VendorDto>?
)
