package com.example.mygarden.compose

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mygarden.compose.gallery.GalleryScreen
import com.example.mygarden.compose.plant.PlantDetailScreen
import com.example.mygarden.compose.plant.PlantList

@Composable
fun MyGardenApp() {
    val navController = rememberNavController()
    val activity = (LocalContext.current as Activity)
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            PlantList(onPlantClick = {
                navController.navigate("plantDetail/${it.plantId}")
            })
        }
        composable(
            "plantDetail/{plantId}",
            arguments = listOf(
                navArgument("plantId") { type = NavType.StringType }
            )
        ) {
            PlantDetailScreen(
                onMoveToGalleryClick = {
                   navController.navigate("gallery/${it}")
                }
            )
        }
        composable("gallery/{plantName}",
            arguments = listOf(
                navArgument("plantName"){ type = NavType.StringType }
            )
        ) {
            GalleryScreen()
        }
    }
}