package com.juanduzac.vendorlust.data.remote.dtos

import com.squareup.moshi.Json

data class OpeningHoursInWeekDto(
    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "monday")
    val monday: List<OpeningHoursInDayDto>?,
    @field:Json(name = "tuesday")
    val tuesday: List<OpeningHoursInDayDto>?,
    @field:Json(name = "wednesday")
    val wednesday: List<OpeningHoursInDayDto>?,
    @field:Json(name = "thursday")
    val thursday: List<OpeningHoursInDayDto>?,
    @field:Json(name = "friday")
    val friday: List<OpeningHoursInDayDto>?,
    @field:Json(name = "saturday")
    val saturday: List<OpeningHoursInDayDto>?,
    @field:Json(name = "sunday")
    val sunday: List<OpeningHoursInDayDto>?,

)
