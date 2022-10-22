package com.juanduzac.vendorlust.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.juanduzac.vendorlust.presentation.navigation.Navigation
import com.juanduzac.vendorlust.presentation.ui.theme.VendorLustTheme
import com.juanduzac.vendorlust.presentation.usecases.vendorlist.VendorListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VendorLustTheme {
                Navigation(
                    navController = rememberNavController()
                )
            }
        }
    }
}
