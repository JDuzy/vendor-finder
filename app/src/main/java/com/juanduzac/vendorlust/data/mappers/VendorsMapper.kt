package com.juanduzac.vendorlust.data.mappers

import com.juanduzac.vendorlust.data.local.entities.VendorEntity
import com.juanduzac.vendorlust.data.local.relations.OpeningHoursInWeekWithOpeningHoursInDay
import com.juanduzac.vendorlust.data.local.relations.vendor.VendorWithOpeningHoursAndHeroImage
import com.juanduzac.vendorlust.data.remote.dtos.VendorDto
import com.juanduzac.vendorlust.data.remote.dtos.VendorsResponseDto
import com.juanduzac.vendorlust.domain.model.OpeningHoursInDay
import com.juanduzac.vendorlust.domain.model.OpeningHoursInWeek
import com.juanduzac.vendorlust.domain.model.Vendor
import com.juanduzac.vendorlust.domain.model.VendorsResponse

fun VendorDto.toVendor(): Vendor {
    return Vendor(
        id = id,
        displayName = displayName,
        name = name,
        description = description,
        contactInfo = contactInfoDto?.toContactInfo(),
        gallery = galleryDto?.map { it.toGalleryItem() },
        openingHours = openingHoursDto?.toOpeningHoursInWeek(),
        heroImage = heroImageDto?.toImage()
    )
}

fun VendorDto.toVendorEntity(): VendorEntity {
    return VendorEntity(
        vendorId = id,
        displayName = displayName,
        name = name,
        description = description,
    )
}

fun VendorsResponseDto.toVendorsResponse(): VendorsResponse {
    return VendorsResponse(
        vendors = vendors?.map { it.toVendor() }
    )
}

fun VendorWithOpeningHoursAndHeroImage.toVendor(): Vendor {

    val openingHoursMapped = OpeningHoursInWeek(
        id = openingHours.openingHoursInWeekEntity.openingHoursInWeekId,
        monday = filterOpeningHours("monday", openingHours),
        tuesday = filterOpeningHours("tuesday", openingHours),
        wednesday = filterOpeningHours("wednesday", openingHours),
        thursday = filterOpeningHours("thursday", openingHours),
        friday = filterOpeningHours("friday", openingHours),
        saturday = filterOpeningHours("saturday", openingHours),
        sunday = filterOpeningHours("sunday", openingHours),

    )

    return Vendor(
        id = vendorEntity.vendorId,
        displayName = vendorEntity.displayName,
        name = vendorEntity.name,
        description = vendorEntity.description,
        contactInfo = null,
        gallery = null,
        openingHours = openingHoursMapped,
        heroImage = imageEntity.toImage()
    )
}

fun filterOpeningHours(
    day: String,
    openingHours: OpeningHoursInWeekWithOpeningHoursInDay
): List<OpeningHoursInDay> {
    return openingHours.openingHoursInDayEntities.filter { it.dayOfWeek == day.toUpperCase() }
        .map { it.toOpeningHoursInDay() }
}

/*
fun VendorEntity.toVendor(): Vendor {
    return Vendor(
        id = id,
        displayName = displayName,
        name = name,
        description = description,
        contactInfo = contactInfo,
        gallery = gallery,
        openingHours = openingHours,
        heroImage = heroImage
    )
}

fun Vendor.toVendorEntity(): VendorEntity {
    return VendorEntity(
        id = id,
        displayName = displayName,
        name = name,
        description = description,
        contactInfo = contactInfo,
        gallery = gallery,
        openingHours = openingHours,
        heroImage = heroImage
    )
}*/
