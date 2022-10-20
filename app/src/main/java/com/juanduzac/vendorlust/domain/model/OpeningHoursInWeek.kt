package com.juanduzac.vendorlust.domain.model

data class OpeningHoursInWeek(
    val id: Int?,
    val monday: List<OpeningHoursInDay>?,
    val tuesday: List<OpeningHoursInDay>?,
    val wednesday: List<OpeningHoursInDay>?,
    val thursday: List<OpeningHoursInDay>?,
    val friday: List<OpeningHoursInDay>?,
    val saturday: List<OpeningHoursInDay>?,
    val sunday: List<OpeningHoursInDay>?,
)
