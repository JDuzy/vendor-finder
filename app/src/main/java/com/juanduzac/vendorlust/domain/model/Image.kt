package com.juanduzac.vendorlust.domain.model

data class Image(
    val id: Long? = null,
    val name: String? = null,
    val alternativeText: String? = null,
    val caption: String? = null,
    val width: Int? = null,
    val height: Int? = null,
    val url: String? = null,
    val previewUrl: String? = null,
)
