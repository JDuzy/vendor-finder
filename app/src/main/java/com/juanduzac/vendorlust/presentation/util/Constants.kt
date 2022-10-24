package com.juanduzac.vendorlust.presentation.util

import com.juanduzac.vendorlust.domain.model.Address
import com.juanduzac.vendorlust.domain.model.ContactInfo
import com.juanduzac.vendorlust.domain.model.GalleryItem
import com.juanduzac.vendorlust.domain.model.Image
import com.juanduzac.vendorlust.domain.model.OpeningHoursInDay
import com.juanduzac.vendorlust.domain.model.OpeningHoursInWeek
import com.juanduzac.vendorlust.domain.model.Vendor

private val description1 = "Cali Dreamin’ Charters is a private yacht company operated by Captain Shahrouz (Scott) Hagizadegan. Guests are driven from Malibu to Marina del Rey where their excursion begins. \n\nThe 54’ Galeon 500 fly is equipped with 3 staterooms, 2 bedrooms, full kitchen with bar and outdoor seating space. Cruise along the coast of California while enjoying delicious food and drink. \n\nPlease contact the concierge for availability and pricing. \n\n"
private val description2 = "\"Visionary design. Artisanal construction. Revolutionary ideas. Kite Eyewear is an independent British brand re-imagining the world of eyewear as you know it.\n\nThe most creative eyewear design team in the country, the most innovative production process using the most high-quality components. The most technologically advanced, sophisticated eye tests available. The most pioneering, personalised shopping experience out there.\n\nIt's time to see differently.\""

private val galleryImage1 = "https://axp-cms-nobu-stack-strapiapps3bucket-172yzo4e7x7hw.s3.eu-west-1.amazonaws.com/NHLS_Kite_2_bc3f40f508.jpg"
private val galleryImage2 = "https://axp-cms-nobu-stack-strapiapps3bucket-172yzo4e7x7hw.s3.eu-west-1.amazonaws.com/NHLS_Ho_H_3_70808c60ed.jpg"

val vendorExample = Vendor(
    name = "Banco Galicia",
    heroImage = Image(
        url =
        "https://axp-cms-nobu-stack-strapiapps3bucket-172yzo4e7x7hw.s3" +
                ".eu-west-1.amazonaws.com/small_NHMB_Faena_2_c8f1ecc1ed.jpg"
    ),
    description = description2,
    contactInfo = ContactInfo(
        phoneNumber = "1156162293",
        emailAddress = "email@address.com",
        websiteUrl = "https://www.cadreamincharters.com",
        address = Address(
            addressLine1 = "Santa fe 2323",
            city = "CABA",
            postalCode = "1232"
        )
    ),
    gallery = listOf(
        GalleryItem(image = Image(url = galleryImage1)),
        GalleryItem(image = Image(url = galleryImage2)),
        GalleryItem(image = Image(url = galleryImage1)),
        GalleryItem(image = Image(url = galleryImage2)),
        GalleryItem(image = Image(url = galleryImage1)),
    ),
    openingHours = OpeningHoursInWeek(
        weeklyOpeningHours =  listOf(
            listOf(
                OpeningHoursInDay(opensAt = "07:00:00", closesAt = "12:00:00"),
                OpeningHoursInDay(opensAt = "13:00:00", closesAt = "19:00:00")
            ),
            listOf(
                OpeningHoursInDay(opensAt = "07:00:00", closesAt = "12:00:00"),
                OpeningHoursInDay(opensAt = "13:00:00", closesAt = "19:00:00")
            ),
        )

    )
)