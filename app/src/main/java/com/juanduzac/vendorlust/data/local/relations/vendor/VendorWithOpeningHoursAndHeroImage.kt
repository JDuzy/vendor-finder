package com.juanduzac.vendorlust.data.local.relations.vendor

import androidx.room.Embedded
import androidx.room.Relation
import com.juanduzac.vendorlust.data.local.entities.ImageEntity
import com.juanduzac.vendorlust.data.local.entities.OpeningHoursInWeekEntity
import com.juanduzac.vendorlust.data.local.entities.VendorEntity
import com.juanduzac.vendorlust.data.local.relations.OpeningHoursInWeekWithOpeningHoursInDay

data class VendorWithOpeningHoursAndHeroImage(
    @Embedded
    var vendorEntity: VendorEntity,

    @Relation(
        entity = OpeningHoursInWeekEntity::class,
        parentColumn = "vendorId",
        entityColumn = "vendorId"
    )
    var openingHours: OpeningHoursInWeekWithOpeningHoursInDay?,

    @Relation(
        parentColumn = "vendorId",
        entityColumn = "vendorId"
    )
    var imageEntity: ImageEntity?
)
