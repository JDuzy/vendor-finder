package com.juanduzac.vendorlust.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VendorEntity(
    @PrimaryKey
    val vendorId: Long? = null,
    val displayName: String? = null,
    val name: String? = null,
    val description: String? = null,
)
