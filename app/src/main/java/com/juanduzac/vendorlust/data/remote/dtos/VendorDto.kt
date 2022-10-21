package com.juanduzac.vendorlust.data.remote.dtos

import com.squareup.moshi.Json

data class VendorDto(
    @field:Json(name = "id")
    val id: Long,
    @field:Json(name = "display_name")
    val displayName: String?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "description")
    val description: String?,
    @field:Json(name = "contact_info")
    val contactInfoDto: ContactInfoDto?,
    @field:Json(name = "gallery")
    val galleryDto: List<GalleryItemDto>?,
    @field:Json(name = "opening_hours")
    val openingHoursDto: OpeningHoursInWeekDto?,
    @field:Json(name = "hero_image")
    val heroImageDto: ImageDto?
)
