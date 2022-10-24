package com.juanduzac.vendorlust.data.local.relations.vendor

import androidx.room.Embedded
import androidx.room.Relation
import com.juanduzac.vendorlust.data.local.entities.ContactInfoEntity
import com.juanduzac.vendorlust.data.local.entities.GalleryItemEntity
import com.juanduzac.vendorlust.data.local.entities.ImageEntity
import com.juanduzac.vendorlust.data.local.entities.OpeningHoursInWeekEntity
import com.juanduzac.vendorlust.data.local.entities.VendorEntity
import com.juanduzac.vendorlust.data.local.relations.GalleryItemAndImage
import com.juanduzac.vendorlust.data.local.relations.OpeningHoursInWeekWithOpeningHoursInDay

data class VendorWithDetail(
    @Embedded
    var vendorEntity: VendorEntity,

    @Relation(
        parentColumn = "vendorId",
        entityColumn = "vendorId"
    )
    var heroImage: ImageEntity,

    @Relation(
        entity = OpeningHoursInWeekEntity::class,
        parentColumn = "vendorId",
        entityColumn = "vendorId"
    )
    var openingHours: OpeningHoursInWeekWithOpeningHoursInDay?,

    @Relation(
        entity = GalleryItemEntity::class,
        parentColumn = "vendorId",
        entityColumn = "vendorId"
    )
    var galleryItems: List<GalleryItemAndImage>,

    @Relation(
        parentColumn = "vendorId",
        entityColumn = "vendorId"
    )
    var contactInfo: ContactInfoEntity,
)
