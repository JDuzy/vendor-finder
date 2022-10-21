package com.juanduzac.vendorlust.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.juanduzac.vendorlust.data.local.entities.GalleryItemEntity

@Dao
interface GalleryItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGalleryItem(galleryItemEntity: GalleryItemEntity)
}
