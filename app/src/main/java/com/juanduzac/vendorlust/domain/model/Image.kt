package com.juanduzac.vendorlust.domain.model

data class Image(
    val id: Int?,
    val name: String?,
    val alternativeText: String?,
    val caption: String?,
    val width: Int?,
    val height: Int?,
    val url: String?,
    val previewUrl: String?,
)
