package com.juanduzac.vendorlust.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.juanduzac.vendorlust.domain.model.ContactInfo
import com.juanduzac.vendorlust.domain.model.GalleryItem
import com.juanduzac.vendorlust.domain.model.Image
import com.juanduzac.vendorlust.domain.model.OpeningHoursInWeek

@Entity
data class VendorEntity(
    @PrimaryKey
    val id: Int? = null,
    val displayName: String?,
    val name: String?,
    val description: String?,
    val contactInfo: ContactInfo?,
    val gallery: List<GalleryItem>?,
    val openingHours: OpeningHoursInWeek?,
    val heroImage: Image?
)