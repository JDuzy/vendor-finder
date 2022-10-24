package com.juanduzac.vendorlust.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.juanduzac.vendorlust.data.local.entities.VendorEntity
import com.juanduzac.vendorlust.data.local.relations.vendor.VendorWithDetail
import com.juanduzac.vendorlust.data.local.relations.vendor.VendorWithOpeningHoursAndHeroImage

@Dao
interface VendorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVendors(vendors: List<VendorEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVendor(vendor: VendorEntity)

    @Query("DELETE FROM vendorentity")
    suspend fun clearVendors()

    @Transaction
    @Query("SELECT * FROM vendorentity")
    suspend fun getVendorsWithOpeningHoursAndHeroImage(): List<VendorWithOpeningHoursAndHeroImage>

    @Transaction //TODO ASK 'AND' OR 'OR' FOR QUERY
    @Query("SELECT * FROM vendorentity WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR LOWER(description) LIKE '%' || LOWER(:query) || '%'")
    suspend fun searchVendorsWithOpeningHoursAndHeroImage(
        query: String
    ): List<VendorWithOpeningHoursAndHeroImage>

    @Transaction
    @Query("SELECT * FROM vendorentity WHERE vendorId = :vendorId")
    suspend fun getVendorDetails(vendorId: Long): VendorWithDetail?
}
