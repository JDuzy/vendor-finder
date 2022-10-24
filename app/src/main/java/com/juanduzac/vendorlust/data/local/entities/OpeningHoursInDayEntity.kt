package com.juanduzac.vendorlust.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OpeningHoursInDayEntity(
    @PrimaryKey
    val openingHoursInDayId: Long? = null,
    val openingHoursInWeekId: Long,
    val opensAt: String? = null,
    val closesAt: String? = null,
    val closesLate: Boolean? = null,
    val dayOfWeek: String?, //TODO Not nullable and refactor to another table
)
