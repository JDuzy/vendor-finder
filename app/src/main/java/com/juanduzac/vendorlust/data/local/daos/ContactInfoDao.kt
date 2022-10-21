package com.juanduzac.vendorlust.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.juanduzac.vendorlust.data.local.entities.ContactInfoEntity

@Dao
interface ContactInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContactInfo(contactInfoEntity: ContactInfoEntity)
}
