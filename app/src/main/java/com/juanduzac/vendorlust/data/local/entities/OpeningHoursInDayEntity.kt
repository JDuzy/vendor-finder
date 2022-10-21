package com.juanduzac.vendorlust.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OpeningHoursInDayEntity(
    @PrimaryKey
    val openingHoursInDayId: Long? = null,
    val openingHoursInWeekId: Long,
    val opensAt: String?,
    val closesAt: String?,
    val closesLate: Boolean?,
    val dayOfWeek: String?,
)
