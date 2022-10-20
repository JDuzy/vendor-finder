package com.juanduzac.vendorlust.data.mappers

import com.juanduzac.vendorlust.data.remote.dtos.GalleryItemDto
import com.juanduzac.vendorlust.domain.model.GalleryItem

fun GalleryItemDto.toGalleryItem(): GalleryItem {
    return GalleryItem(
        id = id,
        youtubeVideoId = youtubeVideoId,
        image = imageDto?.toImage()
        )
}