package com.example.mygarden.feature

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.mygarden.compose.PlantMusicScreen

const val plantMusicRoute = "music_route"

fun NavController.navigateToPlantMusic(navOptions: NavOptions? = null) {
    this.navigate(plantMusicRoute, navOptions)
}

fun NavGraphBuilder.plantMusicScreen(
 //   onBackClick: () -> Unit,
) {
    // TODO: Handle back stack for each top-level destination. At the moment each top-level
    // destination may have own search screen's back stack.
    composable(route = plantMusicRoute) {
        PlantMusicScreen(

        )
    }
}