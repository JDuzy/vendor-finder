package com.juanduzac.vendorlust.domain.model

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime

data class OpeningHoursInWeek(
    val id: Long? = null,
    val monday: List<OpeningHoursInDay>? = null,
    val tuesday: List<OpeningHoursInDay>? = null,
    val wednesday: List<OpeningHoursInDay>? = null,
    val thursday: List<OpeningHoursInDay>? = null,
    val friday: List<OpeningHoursInDay>? = null,
    val saturday: List<OpeningHoursInDay>? = null,
    val sunday: List<OpeningHoursInDay>? = null
) {

    fun isOpen(): Boolean {
        getOpeningHoursForDay(LocalDateTime.now().dayOfWeek)?.forEach {
            if (it.isOpenAt(LocalTime.now())) return true
        }
        return false
    }

    fun getOpeningHoursForDay(dayOfWeek: DayOfWeek): List<OpeningHoursInDay>? {
        return when (dayOfWeek) {
            DayOfWeek.MONDAY -> monday
            DayOfWeek.TUESDAY -> tuesday
            DayOfWeek.WEDNESDAY -> wednesday
            DayOfWeek.THURSDAY -> thursday
            DayOfWeek.FRIDAY -> friday
            DayOfWeek.SATURDAY -> saturday
            DayOfWeek.SUNDAY -> sunday
            else -> null
        }
    }

    fun getWeeklyOpeningHoursText(): List<String> { // TODO IMPLEMENT
        return listOf(
            "Monday 7:00 - 20:00",
            "Tuesday 7:00 - 20:00",
            "Wednesday 7:00 - 20:00",
            "Thursday 7:00 - 20:00",
            "Friday 7:00 - 20:00",
            "Saturday 7:00 - 20:00",
            "Sunday 7:00 - 20:00"
        )
    }

    fun getOpeningHoursTextForToday(): String {
        val openingHoursForToday = getOpeningHoursForDay(LocalDateTime.now().dayOfWeek)
        val openClosedString = " ${if (isOpen()) "Open" else "Closed"}"
        var openingHoursString = ""

        openingHoursForToday?.forEachIndexed { i, hour ->
            openingHoursString += "${hour.opensAt?.dropLast(3)} - ${
                hour.closesAt?.dropLast(
                    3
                )
            }${if (i < openingHoursForToday.size - 1) ", " else ""}"
        }

        return "$openClosedString $openingHoursString"
    }
}
