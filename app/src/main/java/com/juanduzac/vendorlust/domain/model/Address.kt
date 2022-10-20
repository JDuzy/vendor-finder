package com.juanduzac.vendorlust.domain.model

data class Address(
    val id: Int?,
    val addressLine1: String?,
    val addressLine2: String?,
    val city: String?,
    val state: String?,
    val postalCode: String?,
    val country: String?,
    val region: String?,
)
