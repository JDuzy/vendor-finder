package com.juanduzac.vendorlust.data.remote.dtos

import com.squareup.moshi.Json

data class VendorDto(
    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "display_name")
    val displayName: String?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "description")
    val description: String?,
    @field:Json(name = "contact_info")
    val contactInfo: ContactInfoDto?,
    @field:Json(name = "gallery")
    val gallery: List<GalleryItemDto>?,
    @field:Json(name = "opening_hours")
    val openingHours: OpeningHoursInWeekDto?,
    @field:Json(name = "hero_image")
    val heroImage: ImageDto?
)
