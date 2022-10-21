package com.juanduzac.vendorlust.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OpeningHoursInWeekEntity(
    @PrimaryKey
    val openingHoursInWeekId: Long?,
    val vendorId: Long,
)
