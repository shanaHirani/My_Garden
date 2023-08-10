package com.example.mygarden.navigation

import android.app.Activity
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ShareCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mygarden.compose.PlantDetailScreen
import com.example.mygarden.feature.galleryScreen
import com.example.mygarden.feature.homeRoute
import com.example.mygarden.feature.homeScreen
import com.example.mygarden.feature.myGardenScreen
import com.example.mygarden.feature.plantMusicScreen
import com.example.mygarden.feature.shopsLocationScreen
import com.example.mygarden.ui.MyGardenAppState

@Composable
fun MyGardenNavHost(
    appState: MyGardenAppState,
    modifier: Modifier = Modifier,
    startDestination: String = homeRoute,
) {
    val activity = (LocalContext.current as Activity)
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeScreen { navController.navigate("plantDetail/${it.plantId}") }
        galleryScreen { navController.navigateUp() }
        shopsLocationScreen()
        plantMusicScreen()
        myGardenScreen()

        composable(
            "plantDetail/{plantId}",
            arguments = listOf(
                navArgument("plantId") { type = NavType.StringType }
            )
        ) {
            PlantDetailScreen(
                onMoveToGalleryClick = {plantName->
                    navController.navigate("gallery_route/${plantName}")
                },
                onBackClick = {
                    navController.navigateUp()
                },
                onShareClick = {
                    createShareIntent(activity,it)
                }
            )
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