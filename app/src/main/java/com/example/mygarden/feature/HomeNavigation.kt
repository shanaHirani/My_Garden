package com.example.mygarden.feature

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.mygarden.compose.home.HomeScreen
import com.example.mygarden.data.model.domainModel.Plant

const val homeRoute = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(homeRoute, navOptions)
}

fun NavGraphBuilder.homeScreen(
    onPlantClick: (Plant) -> Unit
) {
    // TODO: Handle back stack for each top-level destination. At the moment each top-level
    // destination may have own search screen's back stack.
    composable(route = homeRoute) {
        HomeScreen(
            onPlantClick = onPlantClick
        )
    }
}