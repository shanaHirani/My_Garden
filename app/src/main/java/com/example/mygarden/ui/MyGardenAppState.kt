package com.example.mygarden.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.tracing.trace
import com.example.mygarden.feature.galleryRoute
import com.example.mygarden.feature.homeRoute
import com.example.mygarden.feature.myGardenRoute
import com.example.mygarden.feature.navigateToGallery
import com.example.mygarden.feature.navigateToHome
import com.example.mygarden.feature.navigateToMyGarden
import com.example.mygarden.feature.navigateToPlantMusic
import com.example.mygarden.feature.navigateToShopsLocations
import com.example.mygarden.feature.plantMusicRoute
import com.example.mygarden.feature.shopsLocationRoute
import com.example.mygarden.navigation.TopLevelDestination
import com.example.mygarden.shared.TrackDisposableJank
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberMyGardenAppState(
    //  windowSizeClass: WindowSizeClass,
    //  networkMonitor: NetworkMonitor,
    //   userNewsResourceRepository: UserNewsResourceRepository,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): MyGardenAppState {
    NavigationTrackingSideEffect(navController)
    return remember(
        navController,
        coroutineScope,
//        windowSizeClass,
//        networkMonitor,
//        userNewsResourceRepository,
    ) {
        MyGardenAppState(
            navController,
            coroutineScope,
//            windowSizeClass,
//            networkMonitor,
//            userNewsResourceRepository,
        )
    }
}

@Stable
class MyGardenAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    //val windowSizeClass: WindowSizeClass,
    //networkMonitor: NetworkMonitor,
    //userNewsResourceRepository: UserNewsResourceRepository,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            homeRoute -> TopLevelDestination.HOME
            myGardenRoute -> TopLevelDestination.MY_GARDEN
            shopsLocationRoute -> TopLevelDestination.LOCATION
            plantMusicRoute -> TopLevelDestination.MUSIC
            galleryRoute -> TopLevelDestination.GALLERY
            else -> null
        }

    val shouldShowBottomBar: Boolean
        @Composable get() {
            return !(currentDestination?.route?.contains("plantDetail", true) ?: false)
        }


    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()


    /**
     * UI logic for navigating to a top level destination in the app. Top level destinations have
     * only one copy of the destination of the back stack, and save and restore state whenever you
     * navigate to and from it.
     *
     * @param topLevelDestination: The destination the app needs to navigate to.
     */
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }

            when (topLevelDestination) {
                TopLevelDestination.HOME -> navController.navigateToHome(topLevelNavOptions)
                TopLevelDestination.MY_GARDEN -> navController.navigateToMyGarden(topLevelNavOptions)
                TopLevelDestination.LOCATION -> navController.navigateToShopsLocations(
                    topLevelNavOptions
                )

                TopLevelDestination.MUSIC -> navController.navigateToPlantMusic(topLevelNavOptions)
                TopLevelDestination.GALLERY -> navController.navigateToGallery(topLevelNavOptions)
            }
        }
    }

//    fun navigateToSearch() {
//        navController.navigateToSearch()
//    }
}

@Composable
private fun NavigationTrackingSideEffect(navController: NavHostController) {
    TrackDisposableJank(navController) { metricsHolder ->
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            metricsHolder.state?.putState("Navigation", destination.route.toString())
        }

        navController.addOnDestinationChangedListener(listener)

        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }
}