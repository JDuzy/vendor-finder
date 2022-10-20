package com.juanduzac.vendorlust.data.mappers

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