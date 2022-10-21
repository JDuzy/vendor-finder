package com.juanduzac.vendorlust.data.remote.dtos

import com.squareup.moshi.Json

data class GalleryItemDto(
    @field:Json(name = "id")
    val id: Long,
    @field:Json(name = "image")
    val imageDto: ImageDto?,
)
