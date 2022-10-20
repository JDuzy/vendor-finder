package com.juanduzac.vendorlust.data.mappers

import com.juanduzac.vendorlust.data.remote.dtos.VendorDto
import com.juanduzac.vendorlust.data.remote.dtos.VendorsResponseDto
import com.juanduzac.vendorlust.domain.model.Vendor
import com.juanduzac.vendorlust.domain.model.VendorsResponse

fun VendorDto.toVendor(): Vendor {
    return Vendor(
        id = id,
        displayName = displayName,
        name = name,
        description = description,
        contactInfo = contactInfoDto?.toContactInfo(),
        gallery = galleryDto?.map { it.toGalleryItem() },
        openingHours = openingHoursDto?.toOpeningHoursInWeek(),
        heroImage = heroImageDto?.toImage()
    )
}

fun VendorsResponseDto.toVendorsResponse(): VendorsResponse {
    return VendorsResponse(
        vendors = vendors?.map { it.toVendor() }
    )
}
