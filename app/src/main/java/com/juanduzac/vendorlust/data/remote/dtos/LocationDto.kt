package com.juanduzac.vendorlust.data.remote.dtos

import com.squareup.moshi.Json

data class LocationDto(
    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "latitude")
    val latitude: Float?,
    @field:Json(name = "longitude")
    val longitude: Float?,
)
