package com.juanduzac.vendorlust.data.remote.dtos

import com.squareup.moshi.Json

data class OpeningHoursInDayDto(
    @field:Json(name = "id")
    val id: Long?,
    @field:Json(name = "opens_at")
    val opensAt: String?,
    @field:Json(name = "closes_at")
    val closesAt: String?,
    @field:Json(name = "closes_late")
    val closesLate: Boolean?,
)
