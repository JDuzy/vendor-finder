package com.juanduzac.vendorlust.data.mappers

import com.juanduzac.vendorlust.data.remote.dtos.ContactInfoDto
import com.juanduzac.vendorlust.domain.model.ContactInfo

fun ContactInfoDto.toContactInfo(): ContactInfo {
    return ContactInfo(
        id = id,
        phoneNumber = phoneNumber,
        emailAddress = emailAddress,
        websiteUrl = websiteUrl,
        location = locationDto?.toLocation(),
        address = addressDto?.toAddress()
    )
}
