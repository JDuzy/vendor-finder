package com.juanduzac.vendorlust.domain.model

import com.juanduzac.vendorlust.domain.model.Day.FRIDAY
import com.juanduzac.vendorlust.domain.model.Day.MONDAY
import com.juanduzac.vendorlust.domain.model.Day.SATURDAY
import com.juanduzac.vendorlust.domain.model.Day.SUNDAY
import com.juanduzac.vendorlust.domain.model.Day.THURSDAY
import com.juanduzac.vendorlust.domain.model.Day.TUESDAY
import com.juanduzac.vendorlust.domain.model.Day.WEDNESDAY
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime

data class OpeningHoursInWeek(
    val id: Long? = null,
    val weeklyOpeningHours: List<List<OpeningHoursInDay>?>? = MutableList(7) { null }
) {

    fun isOpen(): Boolean {
        getOpeningHoursForDay(LocalDateTime.now().dayOfWeek)?.forEach {
            if (it.isOpenAt(LocalTime.now())) return true
        }
        return false
    }

    private fun getOpeningHoursForDay(dayOfWeek: DayOfWeek): List<OpeningHoursInDay>? {
        return when (dayOfWeek) {
            DayOfWeek.MONDAY -> weeklyOpeningHours?.get(0)
            DayOfWeek.TUESDAY -> weeklyOpeningHours?.get(1)
            DayOfWeek.WEDNESDAY -> weeklyOpeningHours?.get(2)
            DayOfWeek.THURSDAY -> weeklyOpeningHours?.get(3)
            DayOfWeek.FRIDAY -> weeklyOpeningHours?.get(4)
            DayOfWeek.SATURDAY -> weeklyOpeningHours?.get(5)
            DayOfWeek.SUNDAY -> weeklyOpeningHours?.get(6)
            else -> null
        }
    }

    fun getWeeklyOpeningHoursText(): List<String> {
        val result = mutableListOf<String>()

        weeklyOpeningHours?.forEachIndexed { weekIndex, openingHoursInDay ->
            openingHoursInDay?.let { nonNullOpeningHoursInDay ->
                var dayString = getDayName(weekIndex) + " "
                nonNullOpeningHoursInDay.forEachIndexed { index, openingHour ->
                    dayString += openingHour
                        .getOpenHoursText() + if (index < openingHoursInDay.size - 1) ", " else ""
                }
                result.add(dayString)
            }
        }

        return result
    }

    fun getOpeningHoursTextForToday(): String {
        val openingHoursForToday = getOpeningHoursForDay(LocalDateTime.now().dayOfWeek)
        val openClosedString = " ${if (isOpen()) "Open" else "Closed"}"
        var openingHoursString = ""

        openingHoursForToday?.forEachIndexed { i, openingHoursToday ->
            openingHoursString += openingHoursToday
                .getOpenHoursText() + if (i < openingHoursForToday.size - 1) ", " else ""
        }

        return "$openClosedString $openingHoursString"
    }

    private fun getDayName(i: Int): String {
        return when (i) {
            0 -> MONDAY.string
            1 -> TUESDAY.string
            2 -> WEDNESDAY.string
            3 -> THURSDAY.string
            4 -> FRIDAY.string
            5 -> SATURDAY.string
            6 -> SUNDAY.string
            else -> ""
        }
    }
}
