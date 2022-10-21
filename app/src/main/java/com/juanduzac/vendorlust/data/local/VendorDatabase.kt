package com.juanduzac.vendorlust.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.juanduzac.vendorlust.data.local.daos.ContactInfoDao
import com.juanduzac.vendorlust.data.local.daos.GalleryItemDao
import com.juanduzac.vendorlust.data.local.daos.ImageDao
import com.juanduzac.vendorlust.data.local.daos.OpeningHoursInDayDao
import com.juanduzac.vendorlust.data.local.daos.OpeningHoursInWeekDao
import com.juanduzac.vendorlust.data.local.daos.VendorDao
import com.juanduzac.vendorlust.data.local.entities.ContactInfoEntity
import com.juanduzac.vendorlust.data.local.entities.GalleryItemEntity
import com.juanduzac.vendorlust.data.local.entities.ImageEntity
import com.juanduzac.vendorlust.data.local.entities.OpeningHoursInDayEntity
import com.juanduzac.vendorlust.data.local.entities.OpeningHoursInWeekEntity
import com.juanduzac.vendorlust.data.local.entities.VendorEntity

@Database(
    entities = [
        ContactInfoEntity::class,
        GalleryItemEntity::class,
        ImageEntity::class,
        OpeningHoursInDayEntity::class,
        OpeningHoursInWeekEntity::class,
        VendorEntity::class
    ],
    version = 1
)
abstract class VendorDatabase : RoomDatabase() {

    abstract val vendorDao: VendorDao
    abstract val contactInfoDao: ContactInfoDao
    abstract val galleryItemDao: GalleryItemDao
    abstract val imageDao: ImageDao
    abstract val openingHoursInDayDao: OpeningHoursInDayDao
    abstract val openingHoursInWeekDao: OpeningHoursInWeekDao
}
