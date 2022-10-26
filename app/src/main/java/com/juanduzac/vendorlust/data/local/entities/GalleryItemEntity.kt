package com.juanduzac.vendorlust.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "gallery_item",
    foreignKeys = [
        ForeignKey(
            onDelete = ForeignKey.CASCADE,
            entity = VendorEntity::class,
            parentColumns = ["vendorId"],
            childColumns = ["vendorId"]
        )
    ]
)
data class GalleryItemEntity(
    @PrimaryKey
    val galleryItemId: Long? = null,
    val vendorId: Long,
)
