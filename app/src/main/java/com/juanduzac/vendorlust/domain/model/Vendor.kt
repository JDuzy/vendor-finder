package com.juanduzac.vendorlust.domain.model

data class Vendor(
    val id: Long?,
    val displayName: String?,
    val name: String?,
    val description: String?,
    val contactInfo: ContactInfo?,
    val gallery: List<GalleryItem>?,
    val openingHours: OpeningHoursInWeek?,
    val heroImage: Image?
)
