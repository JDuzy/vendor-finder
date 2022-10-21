package com.juanduzac.vendorlust.data.remote.dtos

import com.squareup.moshi.Json

data class ImageDto(
    @field:Json(name = "id")
    val id: Long?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "alternative_text")
    val alternativeText: String?,
    @field:Json(name = "caption")
    val caption: String?,
    @field:Json(name = "width")
    val width: Int?,
    @field:Json(name = "height")
    val height: Int?,
    @field:Json(name = "url")
    val url: String?,
    @field:Json(name = "previewUrl")
    val previewUrl: String?,
)
