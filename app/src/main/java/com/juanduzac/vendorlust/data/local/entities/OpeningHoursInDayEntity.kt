package com.juanduzac.vendorlust.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "opening_hours_in_day",
    foreignKeys = [
        ForeignKey(
            onDelete = ForeignKey.CASCADE,
            entity = OpeningHoursInWeekEntity::class,
            parentColumns = ["openingHoursInWeekId"],
            childColumns = ["openingHoursInWeekId"]
        )
    ]
)
data class OpeningHoursInDayEntity(
    @PrimaryKey
    val openingHoursInDayId: Long? = null,
    val openingHoursInWeekId: Long,
    val opensAt: String? = null,
    val closesAt: String? = null,
    val closesLate: Boolean? = null,
    val dayId: Long
)
