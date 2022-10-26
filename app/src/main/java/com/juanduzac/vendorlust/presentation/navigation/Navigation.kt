package com.juanduzac.vendorlust.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.juanduzac.vendorlust.presentation.usecases.vendordetail.VendorDetailScreen
import com.juanduzac.vendorlust.presentation.usecases.vendorlist.VendorListViewModel
import com.juanduzac.vendorlust.presentation.usecases.vendorlist.view.VendorListScreen

@Composable
fun Navigation(
    navController: NavHostController,
    viewModel: VendorListViewModel = hiltViewModel(),
    startCallIntent: (String) -> Unit,
    startWebIntent: (String) -> Unit,
    startEmailIntent: (String) -> Unit
) {
    NavHost(navController = navController, startDestination = Screen.VendorListScreen.route) {
        composable(route = Screen.VendorListScreen.route) {
            VendorListScreen(navController, viewModel)
        }
        composable(route = Screen.VendorDetailScreen.route) {
            VendorDetailScreen(
                navController,
                viewModel.selectedVendor,
                startCallIntent,
                startWebIntent,
                startEmailIntent
            )
        }
    }
}
