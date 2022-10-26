package com.juanduzac.vendorlust.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "image"
)
data class ImageEntity(
    @PrimaryKey
    val imageId: Long? = null,
    val vendorId: Long? = null,
    val galleryItemId: Long? = null,
    val name: String? = null,
    val alternativeText: String? = null,
    val caption: String? = null,
    val width: Int? = null,
    val height: Int? = null,
    val url: String? = null,
    val previewUrl: String? = null,
    )
