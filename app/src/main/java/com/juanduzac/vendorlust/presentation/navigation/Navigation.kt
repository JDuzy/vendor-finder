package com.juanduzac.vendorlust.presentation.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.juanduzac.vendorlust.presentation.usecases.vendorlist.VendorListViewModel
import com.juanduzac.vendorlust.presentation.usecases.vendorlist.view.VendorListScreen

private const val VENDOR_ID = "vendorId"

@Composable
fun Navigation(
    navController: NavHostController,
    viewModel: VendorListViewModel = hiltViewModel()
) {
    NavHost(navController = navController, startDestination = Screen.VendorListScreen.route) {
        composable(route = Screen.VendorListScreen.route) {
            VendorListScreen(navController, viewModel)
        }
        composable(
            route = Screen.VendorDetailScreen.route + "/{$VENDOR_ID}",
            arguments = listOf(
                navArgument(VENDOR_ID) {
                    type = NavType.LongType
                }
            )
        ) { entry ->
            Text("Detail screen for vendor with id ${entry.arguments?.getLong(VENDOR_ID)}")
            // VendorDetailsScreen(shopsListViewModel, entry.arguments?.getInt("shopIndex"))
        }
    }
}
