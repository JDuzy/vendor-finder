package com.juanduzac.vendorlust.domain.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.DayOfWeek

data class OpeningHoursInWeek(
    val id: Long?,
    val monday: List<OpeningHoursInDay>?,
    val tuesday: List<OpeningHoursInDay>?,
    val wednesday: List<OpeningHoursInDay>?,
    val thursday: List<OpeningHoursInDay>?,
    val friday: List<OpeningHoursInDay>?,
    val saturday: List<OpeningHoursInDay>?,
    val sunday: List<OpeningHoursInDay>?,
) {

    fun getOpeningHoursForDay(dayOfWeek: DayOfWeek): List<OpeningHoursInDay>? {
        return when (dayOfWeek.value) {
            0 -> monday
            1 -> tuesday
            2 -> wednesday
            3 -> thursday
            4 -> friday
            5 -> saturday
            6 -> sunday
            else -> null
        }
    }
}
