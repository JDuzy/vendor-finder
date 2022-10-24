package com.juanduzac.vendorlust.data.mappers

import com.juanduzac.vendorlust.data.local.entities.VendorEntity
import com.juanduzac.vendorlust.data.local.relations.OpeningHoursInWeekWithOpeningHoursInDay
import com.juanduzac.vendorlust.data.local.relations.vendor.VendorWithDetail
import com.juanduzac.vendorlust.data.local.relations.vendor.VendorWithOpeningHoursAndHeroImage
import com.juanduzac.vendorlust.data.remote.dtos.VendorDto
import com.juanduzac.vendorlust.data.remote.dtos.VendorsResponseDto
import com.juanduzac.vendorlust.domain.model.Day.*
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

    val openingHoursMapped = openingHours?.let {
        getOpeningHoursInWeek(it)
    }

    return Vendor(
        id = vendorEntity.vendorId,
        displayName = vendorEntity.displayName,
        name = vendorEntity.name,
        description = vendorEntity.description,
        contactInfo = null,
        gallery = null,
        openingHours = openingHoursMapped,
        heroImage = imageEntity?.toImage()
    )
}

fun VendorWithDetail.toVendor(): Vendor {

    val openingHoursMapped = openingHours?.let {
        getOpeningHoursInWeek(it)
    }

    with(vendorEntity) {
        return Vendor(
            id = vendorId,
            displayName = displayName,
            name = name,
            description = description,
            contactInfo = contactInfo.toContactInfo(),
            gallery = galleryItems.map { it.toGalleryItem() },
            openingHours = openingHoursMapped,
            heroImage = heroImage.toImage()
        )
    }
}

private fun filterOpeningHours(
    day: String,
    openingHours: OpeningHoursInWeekWithOpeningHoursInDay
): List<OpeningHoursInDay> {
    return openingHours.openingHoursInDayEntities.filter { it.dayOfWeek == day.uppercase() }
        .map { it.toOpeningHoursInDay() }
}

private fun getOpeningHoursInWeek(openingHoursInWeekWithOpeningHoursInDay: OpeningHoursInWeekWithOpeningHoursInDay): OpeningHoursInWeek {
    with(openingHoursInWeekWithOpeningHoursInDay) {
        return OpeningHoursInWeek(
            id = this.openingHoursInWeekEntity.openingHoursInWeekId,
            weeklyOpeningHours = listOf(
                filterOpeningHours(MONDAY.string, this),
                filterOpeningHours(TUESDAY.string, this),
                filterOpeningHours(WEDNESDAY.string, this),
                filterOpeningHours(THURSDAY.string, this),
                filterOpeningHours(FRIDAY.string, this),
                filterOpeningHours(SATURDAY.string, this),
                filterOpeningHours(SUNDAY.string, this),
            )
        )
    }
}


