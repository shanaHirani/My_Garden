package com.example.mygarden.compose

import android.app.Activity
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ShareCompat
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mygarden.compose.gallery.GalleryScreen
import com.example.mygarden.compose.home.HomeScreen

@Composable
fun MyGardenApp(navController:NavHostController) {
    val activity = (LocalContext.current as Activity)
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(onPlantClick = {
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
                },
                onUpClick = {
                    navController.navigateUp()
                },
                onShareClick = {
                    createShareIntent(activity,it)
                }
            )
        }
        composable("gallery/{plantName}",
            arguments = listOf(
                navArgument("plantName") {
                    nullable = true
                    type = NavType.StringType
                }
            )
        ) {
            GalleryScreen(
                onUpClick = {
                    navController.navigateUp()
                }
            )
        }
        composable("my garden") {
            MyGardenScreen()
        }
        composable("shop location") {
            ShopsLocationScreen()
        }
        composable("music") {
            PlantMusicScreen()
        }
    }
}

private fun createShareIntent(activity: Activity, plantDescription: String) {
    val shareText = plantDescription
    val shareIntent = ShareCompat.IntentBuilder(activity)
        .setText(shareText)
        .setType("text/plain")
        .createChooserIntent()
        .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
    activity.startActivity(shareIntent)
}