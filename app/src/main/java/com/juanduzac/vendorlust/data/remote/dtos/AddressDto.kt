package com.juanduzac.vendorlust.data.remote.dtos

import com.squareup.moshi.Json

data class AddressDto(
    @field:Json(name = "address_line_1")
    val addressLine1: String?,
    @field:Json(name = "address_line_2")
    val addressLine2: String?,
    @field:Json(name = "city")
    val city: String?,
    @field:Json(name = "state")
    val state: String?,
    @field:Json(name = "postal_code")
    val postalCode: String?,
    @field:Json(name = "country")
    val country: String?,
    @field:Json(name = "region")
    val region: String?,
)
