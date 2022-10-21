package com.juanduzac.vendorlust.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageEntity(
    @PrimaryKey
    val imageId: Long? = null,
    val vendorId: Long? = null,
    val galleryItemId: Long? = null,
    val name: String?,
    val alternativeText: String?,
    val caption: String?,
    val width: Int?,
    val height: Int?,
    val url: String?,
    val previewUrl: String?,

)
