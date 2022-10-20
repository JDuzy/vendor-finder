package com.juanduzac.vendorlust.data.mappers

import com.juanduzac.vendorlust.data.remote.dtos.AddressDto
import com.juanduzac.vendorlust.domain.model.Address

fun AddressDto.toAddress(): Address {
    return Address(
        id = id,
        addressLine1 = addressLine1,
        addressLine2 = addressLine2,
        city = city,
        state = state,
        postalCode = postalCode,
        country = country,
        region = region
    )
}