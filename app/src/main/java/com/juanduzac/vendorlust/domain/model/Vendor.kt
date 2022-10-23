package com.juanduzac.vendorlust.domain.model

import java.time.LocalDateTime
import java.time.LocalTime

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
        val openingHoursInDay = openingHours?.getOpeningHoursForDay(LocalDateTime.now().dayOfWeek)
        openingHoursInDay?.forEach {
            if (it.isOpenAt(LocalTime.now())) return true
        }
        return false
    }
}
