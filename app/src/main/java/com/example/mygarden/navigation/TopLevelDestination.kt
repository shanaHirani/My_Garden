package com.example.mygarden.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mygarden.R

enum class TopLevelDestination(
    val icon: Int,
    val title: Int,
) {
    HOME(
        R.drawable.ic_home,
        R.string.bottom_bar_home
    ),
    MY_GARDEN(
        R.drawable.ic_my_garden,
        R.string.bottom_bar_my_garden
    ),
    LOCATION(
        R.drawable.ic_location,
        R.string.bottom_bar_location
    ),
    MUSIC(
        R.drawable.ic_music,
        R.string.bottom_bar_music
    ),
    GALLERY(
        R.drawable.ic_gallery,
        R.string.bottom_bar_gallery
    ),
}
