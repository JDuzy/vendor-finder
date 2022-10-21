package com.juanduzac.vendorlust.data.mappers

import com.juanduzac.vendorlust.data.local.entities.ImageEntity
import com.juanduzac.vendorlust.data.remote.dtos.ImageDto
import com.juanduzac.vendorlust.domain.model.Image

fun ImageDto.toImage(): Image {
    return Image(
        id = id,
        name = name,
        alternativeText = alternativeText,
        caption = caption,
        width = width,
        height = height,
        url = url,
        previewUrl = previewUrl
    )
}

fun ImageDto.toImageEntity(vendorId: Long? = null, galleryItemId: Long? = null): ImageEntity {
    return ImageEntity(
        imageId = id,
        vendorId = vendorId,
        galleryItemId = galleryItemId,
        name = name,
        alternativeText = alternativeText,
        caption = caption,
        width = width,
        height = height,
        url = url,
        previewUrl = previewUrl
    )
}

fun ImageEntity.toImage(): Image {
    return Image(
        id = imageId,
        name = name,
        alternativeText = alternativeText,
        caption = caption,
        width = width,
        height = height,
        url = url,
        previewUrl = previewUrl
    )
}
