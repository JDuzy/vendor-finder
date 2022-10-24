package com.juanduzac.vendorlust.domain.model

data class Address(
    val addressLine1: String? = null,
    val addressLine2: String? = null,
    val city: String? = null,
    val state: String? = null,
    val postalCode: String? = null,
    val country: String? = null,
    val region: String? = null,
)
