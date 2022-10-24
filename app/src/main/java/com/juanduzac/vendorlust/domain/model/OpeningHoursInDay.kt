package com.juanduzac.vendorlust.domain.model

import java.time.LocalTime
import java.time.format.DateTimeFormatter

data class OpeningHoursInDay(
    val id: Long? = null,
    val opensAt: String? = null,
    val closesAt: String? = null,
    val closesLate: Boolean? = null,
) {
    fun isOpenAt(time: LocalTime): Boolean {
        val openingTime = LocalTime.parse(opensAt, DateTimeFormatter.ISO_LOCAL_TIME)
        val closingTime = LocalTime.parse(closesAt, DateTimeFormatter.ISO_LOCAL_TIME)
        return time.isAfter(openingTime) && time.isBefore(closingTime)
    }
}
