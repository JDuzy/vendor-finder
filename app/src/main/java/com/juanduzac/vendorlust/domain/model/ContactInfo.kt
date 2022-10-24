package com.juanduzac.vendorlust.domain.model

data class ContactInfo(
    val id: Long? = null,
    val phoneNumber: String? = null,
    val emailAddress: String? = null,
    val websiteUrl: String? = null,
    val location: Location? = null,
    val address: Address? = null,
)
