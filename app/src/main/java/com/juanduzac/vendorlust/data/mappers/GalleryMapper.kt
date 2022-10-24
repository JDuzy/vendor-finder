package com.juanduzac.vendorlust.data.mappers

import com.juanduzac.vendorlust.data.local.entities.GalleryItemEntity
import com.juanduzac.vendorlust.data.local.relations.GalleryItemAndImage
import com.juanduzac.vendorlust.data.remote.dtos.GalleryItemDto
import com.juanduzac.vendorlust.domain.model.GalleryItem

fun GalleryItemDto.toGalleryItem(): GalleryItem {
    return GalleryItem(
        id = id,
        image = imageDto?.toImage()
    )
}

fun GalleryItemDto.toGalleryItemEntity(vendorId: Long): GalleryItemEntity {
    return GalleryItemEntity(
        galleryItemId = id,
        vendorId = vendorId
    )
}

fun GalleryItemAndImage.toGalleryItem(): GalleryItem {
    return GalleryItem(
        id = galleryItemEntity?.galleryItemId,
        image = imageEntity?.toImage()
    )
}
