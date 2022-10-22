package com.juanduzac.vendorlust.presentation.navigation

sealed class Screen(val route: String){
    object VendorListScreen : Screen("vendor_list_screen")
    object VendorDetailScreen : Screen("vendor_detail_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

    fun withArgs(vararg args: Long): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}