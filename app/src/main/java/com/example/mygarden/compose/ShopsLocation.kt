package com.example.mygarden.compose

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.mygarden.R
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*


@Composable
fun ShopsLocationScreen() {
    val shop1 = LatLng( -33.962149,151.132641)
    val shop2 = LatLng( -33.887551,151.125504)
    val shop3 = LatLng( -33.786247,151.26671)
    val shop4 = LatLng(  -33.77443,150.936513)
    val cameraPositionState = rememberCameraPositionState()
    cameraPositionState.position = CameraPosition.fromLatLngZoom(shop1, 10f)
   GoogleMap(
       modifier=Modifier
           .fillMaxSize()
           .padding(0.dp, 0.dp, 0.dp, dimensionResource(id = R.dimen.bottom_menu_height)),
       cameraPositionState = cameraPositionState
   ){
        Marker(
            state = rememberMarkerState(position = shop1),
            draggable = true,
            title = "hurstville shop",

        )
       Marker(
           state = rememberMarkerState(position = shop2),
           draggable = true,
           title = "burwood shop"
       )
       Marker(
           state = rememberMarkerState(position = shop3),
           draggable = true,
           title = "manly shop"
       )
       Marker(
           state = rememberMarkerState(position = shop4),
           draggable = true,
           title = "blacktown shop"
       )
   }
}