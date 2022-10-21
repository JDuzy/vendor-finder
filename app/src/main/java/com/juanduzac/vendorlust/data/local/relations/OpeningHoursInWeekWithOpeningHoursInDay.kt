package com.juanduzac.vendorlust.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.juanduzac.vendorlust.data.local.entities.OpeningHoursInDayEntity
import com.juanduzac.vendorlust.data.local.entities.OpeningHoursInWeekEntity

data class OpeningHoursInWeekWithOpeningHoursInDay(
    @Embedded
    var openingHoursInWeekEntity: OpeningHoursInWeekEntity,
    @Relation(
        parentColumn = "openingHoursInWeekId",
        entityColumn = "openingHoursInWeekId"
    )
    var openingHoursInDayEntities: List<OpeningHoursInDayEntity>

)
