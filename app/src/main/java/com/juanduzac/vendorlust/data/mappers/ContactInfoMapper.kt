package com.juanduzac.vendorlust.data.mappers

import com.juanduzac.vendorlust.data.local.entities.ContactInfoEntity
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

fun ContactInfoDto.toContactInfoEntity(vendorId: Long): ContactInfoEntity {
    return ContactInfoEntity(
        contactInfoId = id,
        vendorId = vendorId,
        phoneNumber = phoneNumber,
        emailAddress = emailAddress,
        websiteUrl = websiteUrl,
        location = locationDto?.toLocation(),
        address = addressDto?.toAddress()
    )
}

fun ContactInfoEntity.toContactInfo(): ContactInfo{
    return ContactInfo(
        id = contactInfoId,
        phoneNumber = phoneNumber,
        emailAddress = emailAddress,
        websiteUrl = websiteUrl,
        location = location,
        address = address
    )
}
