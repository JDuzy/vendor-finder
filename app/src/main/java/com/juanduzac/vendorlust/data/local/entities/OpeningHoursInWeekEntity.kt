package com.juanduzac.vendorlust.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "opening_hours_in_week",
    foreignKeys = [
        ForeignKey(
            onDelete = CASCADE,
            entity = VendorEntity::class,
            parentColumns = ["vendorId"],
            childColumns = ["vendorId"]
        )]
)
data class OpeningHoursInWeekEntity(
    @PrimaryKey
    val openingHoursInWeekId: Long?,
    val vendorId: Long,
)
