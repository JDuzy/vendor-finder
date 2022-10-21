package com.juanduzac.vendorlust.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.juanduzac.vendorlust.data.local.entities.ImageEntity
import com.juanduzac.vendorlust.data.local.entities.VendorEntity

data class VendorWithOpeningHoursAndHeroImage(
    @Embedded
    val vendorEntity: VendorEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "vendorId"
    )
    val openingHoursInWeekWithOpeningHoursInDay: VendorAndOpeningHoursInWeekWithOpeningHoursInDay,

    @Relation(
        parentColumn = "id",
        entityColumn = "vendorId"
    )
    val imageEntity: ImageEntity
)
