package com.juanduzac.vendorlust.data.mappers

import com.juanduzac.vendorlust.data.remote.dtos.OpeningHoursInDayDto
import com.juanduzac.vendorlust.data.remote.dtos.OpeningHoursInWeekDto
import com.juanduzac.vendorlust.domain.model.OpeningHoursInDay
import com.juanduzac.vendorlust.domain.model.OpeningHoursInWeek

fun OpeningHoursInWeekDto.toOpeningHoursInWeek(): OpeningHoursInWeek {
    return OpeningHoursInWeek(
        id = id,
        monday = monday?.map { it.toOpeningHoursInDay()},
        tuesday = tuesday?.map { it.toOpeningHoursInDay()},
        wednesday = wednesday?.map { it.toOpeningHoursInDay()},
        thursday = thursday?.map { it.toOpeningHoursInDay()},
        friday = friday?.map { it.toOpeningHoursInDay()},
        saturday = saturday?.map { it.toOpeningHoursInDay()},
        sunday = sunday?.map { it.toOpeningHoursInDay()},
    )
}

fun OpeningHoursInDayDto.toOpeningHoursInDay(): OpeningHoursInDay {
    return OpeningHoursInDay(
        id = id,
        opensAt = opensAt,
        closesAt = closesAt,
        closesLate = closesLate
    )
}