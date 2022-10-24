package com.juanduzac.vendorlust.domain.model

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.time.LocalTime

internal class OpeningHoursInDayTest {

    private lateinit var openingHoursInDay: OpeningHoursInDay

    @Before
    fun setup(){
        openingHoursInDay = OpeningHoursInDay(
            opensAt = "07:00:00",
            closesAt = "12:00:00"
        )
    }

    @Test
    fun givenVendorIsOpen_whenIsOpenAtIsCalled_thenReturnsTrue() {
        val openTime = LocalTime.parse("08:00:00")
        assertTrue(openingHoursInDay.isOpenAt(openTime))
    }

    @Test
    fun givenVendorIsClosed_whenIsOpenAtIsCalled_thenReturnsFalse() {
        val openTime = LocalTime.parse("14:00:00")
        assertFalse(openingHoursInDay.isOpenAt(openTime))
    }

    @Test
    fun givenOpensAtIsNull_whenIsOpenAtIsCalled_thenReturnsFalse() {
        openingHoursInDay = OpeningHoursInDay(
            opensAt = null,
            closesAt = "12:00:00"
        )
        val openTime = LocalTime.parse("14:00:00")
        assertFalse(openingHoursInDay.isOpenAt(openTime))
    }

    @Test
    fun givenClosesAtIsNull_whenIsOpenAtIsCalled_thenReturnsFalse() {
        openingHoursInDay = OpeningHoursInDay(
            opensAt = "07:00:00",
            closesAt = null
        )
        val openTime = LocalTime.parse("14:00:00")
        assertFalse(openingHoursInDay.isOpenAt(openTime))
    }

    @Test
    fun whenGetOpenHoursTextIsCalled_ThenTheCorrectStringIsReturned() {
        assertEquals("07:00 - 12:00", openingHoursInDay.getOpenHoursText())
    }
}