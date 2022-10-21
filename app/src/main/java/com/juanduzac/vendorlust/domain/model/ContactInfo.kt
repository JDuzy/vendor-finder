package com.juanduzac.vendorlust.domain.model

data class ContactInfo(
    val id: Long?,
    val phoneNumber: String?,
    val emailAddress: String?,
    val websiteUrl: String?,
    val location: Location?,
    val address: Address?,
)
