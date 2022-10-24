package com.juanduzac.vendorlust.data.mappers

import com.juanduzac.vendorlust.data.local.entities.OpeningHoursInWeekEntity
import com.juanduzac.vendorlust.data.remote.dtos.OpeningHoursInWeekDto
import com.juanduzac.vendorlust.domain.model.OpeningHoursInWeek

fun OpeningHoursInWeekDto.toOpeningHoursInWeek(): OpeningHoursInWeek {
    return OpeningHoursInWeek(
        id = id,
        weeklyOpeningHours = listOf(
            monday?.map { it.toOpeningHoursInDay() },
            tuesday?.map { it.toOpeningHoursInDay() },
            wednesday?.map { it.toOpeningHoursInDay() },
            thursday?.map { it.toOpeningHoursInDay() },
            friday?.map { it.toOpeningHoursInDay() },
            saturday?.map { it.toOpeningHoursInDay() },
            sunday?.map { it.toOpeningHoursInDay() }
        )
    )
}

fun OpeningHoursInWeekDto.toEntity(vendorId: Long): OpeningHoursInWeekEntity {
    return OpeningHoursInWeekEntity(
        openingHoursInWeekId = id,
        vendorId = vendorId
    )
}