package com.juanduzac.vendorlust.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.juanduzac.vendorlust.domain.model.Address
import com.juanduzac.vendorlust.domain.model.Location

@Entity(
    tableName = "contact_info",
    foreignKeys = [
        ForeignKey(
            onDelete = ForeignKey.CASCADE,
            entity = VendorEntity::class,
            parentColumns = ["vendorId"],
            childColumns = ["vendorId"]
        )]
)
data class ContactInfoEntity(
    @PrimaryKey
    val contactInfoId: Long? = null,
    val vendorId: Long,
    val phoneNumber: String?,
    val emailAddress: String?,
    val websiteUrl: String?,
    @Embedded
    val location: Location?,
    @Embedded
    val address: Address?,
)
