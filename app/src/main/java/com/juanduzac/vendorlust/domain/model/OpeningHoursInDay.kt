package com.juanduzac.vendorlust.domain.model

import java.time.LocalTime

data class OpeningHoursInDay(
    val id: Long?,
    val opensAt: String?,
    val closesAt: String?,
    val closesLate: Boolean?,
) {
    fun isOpenAt(time: LocalTime): Boolean {
        val openingTime = LocalTime.parse(opensAt)
        val closingTime = LocalTime.parse(closesAt)
        return time.isAfter(openingTime) && time.isBefore(closingTime)
    }
}
