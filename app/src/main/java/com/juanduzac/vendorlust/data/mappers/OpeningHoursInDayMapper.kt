package com.juanduzac.vendorlust.data.mappers

import com.juanduzac.vendorlust.data.local.entities.OpeningHoursInDayEntity
import com.juanduzac.vendorlust.data.remote.dtos.OpeningHoursInDayDto
import com.juanduzac.vendorlust.domain.model.Day
import com.juanduzac.vendorlust.domain.model.OpeningHoursInDay

fun OpeningHoursInDayDto.toOpeningHoursInDay(): OpeningHoursInDay {
    return OpeningHoursInDay(
        id = id,
        opensAt = opensAt,
        closesAt = closesAt,
        closesLate = closesLate
    )
}

fun OpeningHoursInDayDto.toOpeningHoursInDayEntity(
    dayOfWeek: Day,
    openingHoursInWeekId: Long
): OpeningHoursInDayEntity {
    return OpeningHoursInDayEntity(
        openingHoursInDayId = id,
        openingHoursInWeekId = openingHoursInWeekId,
        opensAt = opensAt,
        closesAt = closesAt,
        closesLate = closesLate,
        dayId = dayOfWeek.id
    )
}

fun OpeningHoursInDayEntity.toOpeningHoursInDay(): OpeningHoursInDay {
    return OpeningHoursInDay(
        id = openingHoursInDayId,
        opensAt = opensAt,
        closesAt = closesAt,
        closesLate = closesLate
    )
}
