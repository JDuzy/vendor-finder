package com.juanduzac.vendorlust.data.mappers

import com.juanduzac.vendorlust.data.remote.dtos.LocationDto
import com.juanduzac.vendorlust.domain.model.Location

fun LocationDto.toLocation(): Location {
    return Location(
        id = id,
        latitude = latitude,
        longitude = longitude
    )
}
