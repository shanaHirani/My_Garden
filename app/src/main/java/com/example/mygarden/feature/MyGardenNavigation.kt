package com.example.mygarden.feature

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.mygarden.compose.MyGardenScreen

const val myGardenRoute = "my_garden_route"

fun NavController.navigateToMyGarden(navOptions: NavOptions? = null) {
    this.navigate(myGardenRoute, navOptions)
}

fun NavGraphBuilder.myGardenScreen(

) {
    // TODO: Handle back stack for each top-level destination. At the moment each top-level
    // destination may have own search screen's back stack.
    composable(route = myGardenRoute) {
        MyGardenScreen(
        )
    }
}