package com.example.mygarden.feature

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.mygarden.compose.ShopsLocationScreen

const val shopsLocationRoute = "shop_Location_route"

fun NavController.navigateToShopsLocations(navOptions: NavOptions? = null) {
    this.navigate(shopsLocationRoute, navOptions)
}

fun NavGraphBuilder.shopsLocationScreen(

) {
    // TODO: Handle back stack for each top-level destination. At the moment each top-level
    // destination may have own search screen's back stack.
    composable(route = shopsLocationRoute) {
        ShopsLocationScreen(
        )
    }
}