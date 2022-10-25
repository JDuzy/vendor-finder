package com.juanduzac.vendorlust.presentation

import androidx.compose.ui.test.assertAll
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import com.juanduzac.vendorlust.presentation.navigation.Navigation
import com.juanduzac.vendorlust.presentation.navigation.Screen
import com.juanduzac.vendorlust.presentation.ui.theme.VendorLustTheme
import com.juanduzac.vendorlust.presentation.usecases.vendorlist.view.VendorItemTestTag
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private var actualRoute: String? = null

    @Before
    fun setUp() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            navController.addOnDestinationChangedListener { nvController, navDestination, bundle ->
                actualRoute = navDestination.route
            }
            VendorLustTheme {
                Navigation(
                    navController = navController,
                    startCallIntent = {},
                    startEmailIntent = {},
                    startWebIntent = {}
                )
            }
        }
    }

    @Test
    fun givenStartDestinationIsVendorListScreen_whenClickingAVendorItem_thenNavigateToVendorDetailScreen() {
        runTest {
            assertEquals(Screen.VendorListScreen.route, actualRoute)

            composeTestRule.onAllNodesWithTag(VendorItemTestTag).assertAll(hasClickAction())
                .onFirst().performClick()

            composeTestRule.awaitIdle()
            assertEquals(Screen.VendorDetailScreen.route, actualRoute)

        }
    }
}