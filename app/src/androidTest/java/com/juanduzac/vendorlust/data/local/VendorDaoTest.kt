@file:OptIn(ExperimentalCoroutinesApi::class)

package com.juanduzac.vendorlust.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.juanduzac.vendorlust.data.local.daos.VendorDao
import com.juanduzac.vendorlust.data.local.entities.ImageEntity
import com.juanduzac.vendorlust.data.local.entities.OpeningHoursInDayEntity
import com.juanduzac.vendorlust.data.local.entities.OpeningHoursInWeekEntity
import com.juanduzac.vendorlust.data.local.entities.VendorEntity
import com.juanduzac.vendorlust.domain.model.Day
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class VendorDaoTest {

    private lateinit var db: VendorDatabase
    private lateinit var dao: VendorDao
    private lateinit var vendor1: VendorEntity
    private lateinit var vendor2: VendorEntity

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            VendorDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = db.vendorDao

        vendor1 = VendorEntity(
            1,
            "display_name",
            "name1",
            "desc1"
        )

        vendor2 = VendorEntity(
            2,
            "display_name2",
            "name2",
            "desc2"
        )
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertVendor() {
        runTest {
            dao.insertVendor(vendor1)

            val vendors = dao.getVendorsWithOpeningHoursAndHeroImage()

            assertEquals(vendor1.vendorId, vendors.first().vendorEntity.vendorId)
            assertEquals(vendor1, vendors.first().vendorEntity)
        }
    }

    @Test
    fun insertVendors() {
        runTest {
            val vendorsToInsert = listOf(vendor1, vendor2)

            dao.insertVendors(vendorsToInsert)

            val vendors = dao.getVendorsWithOpeningHoursAndHeroImage()

            assertEquals(vendor1, vendors.first().vendorEntity)
            assertEquals(vendor2, vendors.last().vendorEntity)
            assertEquals(vendorsToInsert, vendors.map { it.vendorEntity })
        }
    }

    @Test
    fun clearVendors() {
        runTest {
            // Arrange
            val vendorsToInsert = listOf(vendor1, vendor2)
            dao.insertVendors(vendorsToInsert)

            val vendorsList = dao.getVendorsWithOpeningHoursAndHeroImage()

            assertEquals(vendorsToInsert, vendorsList.map { it.vendorEntity })

            // Act
            dao.clearVendors()

            // Assert
            val vendors = dao.getVendorsWithOpeningHoursAndHeroImage()
            assertEquals(vendors, emptyList<VendorEntity>())
        }
    }

    @Test
    fun getVendorsWithNullOpeningHoursAndHeroImage() {
        runTest {
            // Arrange
            val vendorsToInsert = listOf(vendor1, vendor2)
            dao.insertVendors(vendorsToInsert)

            // Act
            val vendorsList = dao.getVendorsWithOpeningHoursAndHeroImage()

            // Assert
            assertEquals(null, vendorsList.first().openingHours)
            assertEquals(null, vendorsList.first().imageEntity)
            assertEquals(null, vendorsList.last().imageEntity)
            assertEquals(null, vendorsList.last().imageEntity)
        }
    }

    @Test
    fun getVendorsWithOpeningHoursAndHeroImage() {
        runTest {
            // Arrange
            val vendorsToInsert = listOf(vendor1, vendor2)
            dao.insertVendors(vendorsToInsert)

            val openingHoursInWeekDao = db.openingHoursInWeekDao
            val openingHoursInDayDao = db.openingHoursInDayDao
            val imageDao = db.imageDao

            val openingHoursInDayMondayEntity1 = OpeningHoursInDayEntity(
                openingHoursInDayId = 1,
                openingHoursInWeekId = 1,
                opensAt = "07:00:00",
                closesAt = "13:00:00",
                dayOfWeek = Day.MONDAY.string
            )

            val openingHoursInWeekEntity1 = OpeningHoursInWeekEntity(
                openingHoursInWeekId = 1,
                vendorId = 1
            )

            val imageEntity1 = ImageEntity(
                imageId = 1,
                vendorId = 1
            )

            imageDao.insertImage(imageEntity1)
            openingHoursInWeekDao.insertOpeningHoursInWeek(openingHoursInWeekEntity1)
            openingHoursInDayDao.insertOpeningHoursInDay(openingHoursInDayMondayEntity1)

            // Act
            val vendorsList = dao.getVendorsWithOpeningHoursAndHeroImage()

            // Assert
            assertEquals(imageEntity1, vendorsList.first().imageEntity)
            assertEquals(openingHoursInWeekEntity1, vendorsList.first().openingHours?.openingHoursInWeekEntity)
            assertEquals(openingHoursInDayMondayEntity1, vendorsList.first().openingHours?.openingHoursInDayEntities?.first())
        }
    }

    @Test
    fun given2VendorsName_whenSearchingFor1VendorsName_thenReturnsOnly1Vendor(){
        runTest {
            // Arrange
            val vendorsToInsert = listOf(vendor1, vendor2)
            dao.insertVendors(vendorsToInsert)

            // Act
            val listOfVendors = dao.searchVendorsWithOpeningHoursAndHeroImage("name1")

            // Assert
            assertTrue(listOfVendors.map { it.vendorEntity }.contains(vendor1))
            assertFalse(listOfVendors.map { it.vendorEntity }.contains(vendor2))
        }
    }

    @Test
    fun given2VendorsDescription_whenSearchingFor1VendorsDescription_thenReturnsOnly1Vendor(){
        runTest {
            // Arrange
            val vendorsToInsert = listOf(vendor1, vendor2)
            dao.insertVendors(vendorsToInsert)

            // Act
            val listOfVendors = dao.searchVendorsWithOpeningHoursAndHeroImage("desc1")

            // Assert
            assertTrue(listOfVendors.map { it.vendorEntity }.contains(vendor1))
            assertFalse(listOfVendors.map { it.vendorEntity }.contains(vendor2))
        }
    }

    @Test
    fun givenAVendorWithAGivenIdIsOnDB_whenGettingVendorsDetailByThatId_thenReturnsTheVendorDetail(){
        runTest {
            // Arrange
            val vendorsToInsert = listOf(vendor1, vendor2)
            dao.insertVendors(vendorsToInsert)

            // Act
            val vendorWithDetailById = dao.getVendorDetails(1)

            // Assert
            assertEquals(vendor1, vendorWithDetailById?.vendorEntity)
        }
    }

    @Test
    fun givenAVendorWithAGivenIdIsOnDB_whenGettingVendorsDetailByWrongId_thenReturnsNull(){
        runTest {
            // Arrange
            val vendorsToInsert = listOf(vendor1, vendor2)
            dao.insertVendors(vendorsToInsert)

            // Act
            val vendorWithDetailById = dao.getVendorDetails(3)

            // Assert
            assertEquals(null, vendorWithDetailById)
        }
    }

}