package com.juanduzac.vendorlust.data.remote.dtos

import com.squareup.moshi.Json

data class ContactInfoDto(
    @field:Json(name = "id")
    val id: Long,
    @field:Json(name = "phone_number")
    val phoneNumber: String?,
    @field:Json(name = "email_address")
    val emailAddress: String?,
    @field:Json(name = "website_url")
    val websiteUrl: String?,
    @field:Json(name = "location")
    val locationDto: LocationDto?,
    @field:Json(name = "address")
    val addressDto: AddressDto?,
)
