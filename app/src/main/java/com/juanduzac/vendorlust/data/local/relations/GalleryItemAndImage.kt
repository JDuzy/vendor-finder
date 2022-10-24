package com.juanduzac.vendorlust.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.juanduzac.vendorlust.data.local.entities.GalleryItemEntity
import com.juanduzac.vendorlust.data.local.entities.ImageEntity

data class GalleryItemAndImage(
    @Embedded
    var galleryItemEntity: GalleryItemEntity?,
    @Relation(
        parentColumn = "galleryItemId",
        entityColumn = "galleryItemId"
    )
    var imageEntity: ImageEntity?
)
