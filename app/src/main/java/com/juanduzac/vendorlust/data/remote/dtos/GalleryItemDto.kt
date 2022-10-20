package com.juanduzac.vendorlust.data.remote.dtos

import com.squareup.moshi.Json

data class GalleryItemDto(
    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "youtube_video_id")
    val youtubeVideoId: Int?,
    @field:Json(name = "image")
    val imageDto: ImageDto?,
)
