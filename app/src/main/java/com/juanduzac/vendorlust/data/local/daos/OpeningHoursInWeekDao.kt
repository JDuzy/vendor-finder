package com.juanduzac.vendorlust.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.juanduzac.vendorlust.data.local.entities.OpeningHoursInWeekEntity

@Dao
interface OpeningHoursInWeekDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOpeningHoursInWeek(openingHoursInWeekEntity: OpeningHoursInWeekEntity)
}
