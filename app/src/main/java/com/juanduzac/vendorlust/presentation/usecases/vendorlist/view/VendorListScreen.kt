package com.juanduzac.vendorlust.presentation.usecases.vendorlist.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.juanduzac.vendorlust.presentation.navigation.Screen
import com.juanduzac.vendorlust.presentation.usecases.vendorlist.VendorListViewModel

@Composable
fun VendorListScreen(
    navController: NavController,
    viewModel: VendorListViewModel = hiltViewModel()
) {
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.isRefreshing
    )
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
        ) {
            OutlinedTextField(
                value = viewModel.searchQuery,
                onValueChange = { newQuery ->
                    viewModel.searchQuery = newQuery
                    viewModel.searchVendor()
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Search")
                },
                singleLine = true
            )

            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = { viewModel.getVendorsList(true) }
            ) {
                viewModel.vendorsResponse.vendors?.let { vendors ->
                    LazyColumn(modifier = Modifier.padding(top = 12.dp)) {
                        items(vendors) { vendor ->
                            VendorItem(
                                vendor = vendor,
                                isOpen = vendor.isOpen(),
                                onClick = {
                                    it.id?.let { id ->
                                        navController.navigate(
                                            Screen.VendorDetailScreen.withArgs(id)
                                        )
                                    }
                                }
                            )

                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }
}
