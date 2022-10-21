package com.juanduzac.vendorlust.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.juanduzac.vendorlust.data.local.entities.ContactInfoEntity
import com.juanduzac.vendorlust.data.local.entities.GalleryItemEntity
import com.juanduzac.vendorlust.data.local.entities.OpeningHoursInWeekEntity
import com.juanduzac.vendorlust.data.local.entities.VendorEntity
import com.juanduzac.vendorlust.data.local.relations.VendorAndContactInfo
import com.juanduzac.vendorlust.data.local.relations.VendorAndOpeningHoursInWeekWithOpeningHoursInDay
import com.juanduzac.vendorlust.data.local.relations.VendorWithOpeningHoursAndHeroImage

@Dao
interface VendorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVendor(vendor: VendorEntity)

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContactInfo(contactInfoEntity: ContactInfoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGalleryItem(galleryItemEntity: GalleryItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOpeningHoursInWeek(openingHoursInWeekEntity: OpeningHoursInWeekEntity)*/

    @Transaction
    @Query("SELECT * FROM vendorentity")
    suspend fun getVendorsWithOpeningHoursAndHeroImage(): VendorWithOpeningHoursAndHeroImage

    @Transaction
    @Query("SELECT * FROM vendorentity WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%'")
    suspend fun searchVendorsWithOpeningHoursAndHeroImage(query: String): VendorWithOpeningHoursAndHeroImage

}