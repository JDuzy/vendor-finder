package com.juanduzac.vendorlust.presentation.usecases.vendorlist.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.juanduzac.vendorlust.domain.model.Vendor

@Composable
fun VendorListScreen(navigateToVendorDetail: (Vendor) -> Unit) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        LazyColumn(Modifier.padding(16.dp)) {
            items(vendors) { vendor ->
                VendorItem(
                    vendor = vendor,
                    isOpen = vendor.isOpen(),
                    onClick = navigateToVendorDetail
                )
            }
        }
    }
}