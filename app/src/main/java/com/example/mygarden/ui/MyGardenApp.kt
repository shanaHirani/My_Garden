package com.example.mygarden.ui



import android.annotation.SuppressLint
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.mygarden.R
import com.example.mygarden.navigation.MyGardenNavHost
import com.example.mygarden.navigation.TopLevelDestination
import com.example.mygarden.ui.theme.DeepGreen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyGardenApp(
    modifier: Modifier = Modifier,
    appState: MyGardenAppState = rememberMyGardenAppState()

) {
    Scaffold(
        modifier = modifier,
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        bottomBar = {
            if (appState.shouldShowBottomBar){
                MyGardenBottomBar(
                    destinations = appState.topLevelDestinations,
                    onNavigateToDestination = appState::navigateToTopLevelDestination,
                    currentDestination = appState.currentDestination
                )
            }
        }

    ) {
        MyGardenNavHost(appState = appState)
    }
}

@Composable
private fun MyGardenBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?
) {
    BottomNavigation(
        backgroundColor = DeepGreen,
        modifier = Modifier.height(dimensionResource(id = R.dimen.bottom_menu_height))
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            val contentColor = if (selected) Color.Black else Color.Black.copy(0.4f)
            BottomNavigationItem(
                selected = selected,
                alwaysShowLabel = false,
                icon = {
                    Icon(
                        painterResource(id = destination.iconDrawableId),
                        contentDescription = "hi",
                        modifier = Modifier.size(dimensionResource(id = R.dimen.bottom_menu_icon_size)),
                        tint = contentColor
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = destination.title),
                        style = TextStyle(fontSize = 10.sp, color = contentColor)
                    )
                },
                onClick = {
                    onNavigateToDestination(destination)
                }
            )
        }
    }

}


private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination):Boolean {
    return this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
}

