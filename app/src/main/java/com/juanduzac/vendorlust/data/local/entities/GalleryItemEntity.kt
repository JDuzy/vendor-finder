package com.juanduzac.vendorlust.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GalleryItemEntity(
    @PrimaryKey
    val galleryItemId: Long? = null,
    val vendorId: Long,
)
