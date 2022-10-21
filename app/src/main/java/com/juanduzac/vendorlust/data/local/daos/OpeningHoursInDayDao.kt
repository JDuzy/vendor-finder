package com.juanduzac.vendorlust.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.juanduzac.vendorlust.data.local.entities.OpeningHoursInDayEntity

@Dao
interface OpeningHoursInDayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOpeningHoursInDay(openingHoursInDayEntity: OpeningHoursInDayEntity)
}
