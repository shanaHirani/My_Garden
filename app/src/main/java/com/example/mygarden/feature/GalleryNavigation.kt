package com.example.mygarden.feature

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mygarden.compose.PlantMusicScreen
import com.example.mygarden.compose.gallery.GalleryScreen

const val Plant_Name = "plantName"
const val galleryRoute = "gallery_route/{$Plant_Name}"

fun NavController.navigateToGallery(navOptions: NavOptions? = null) {
    this.navigate(galleryRoute, navOptions)
}

fun NavGraphBuilder.galleryScreen(
    onUpClick: () -> Unit
) {
    // TODO: Handle back stack for each top-level destination. At the moment each top-level
    // destination may have own search screen's back stack.
    composable(
        route = galleryRoute,
        arguments = listOf(
            navArgument(Plant_Name) {
                type = NavType.StringType
                nullable = true
            },
        )
    ) {
        GalleryScreen()
    }
}