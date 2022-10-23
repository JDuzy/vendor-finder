package com.juanduzac.vendorlust.domain.model

data class Vendor(
    val id: Long? = null,
    val displayName: String? = null,
    val name: String? = null,
    val description: String? = null,
    val contactInfo: ContactInfo? = null,
    val gallery: List<GalleryItem>? = null,
    val openingHours: OpeningHoursInWeek? = null,
    val heroImage: Image? = null
) {

    fun isOpen(): Boolean {
        return true // TODO IMPLEMENT
    }
}