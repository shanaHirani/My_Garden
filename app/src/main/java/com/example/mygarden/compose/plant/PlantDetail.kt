package com.example.mygarden.compose.plant

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.mygarden.data.model.domainModel.Plant
import com.example.mygarden.viewmodels.PlantDetailViewModel

@Composable
fun PlantDetailScreen(
    plantDetailsViewModel: PlantDetailViewModel = hiltViewModel(),
    onMoveToGalleryClick: (String) -> Unit
) {
    val plant = plantDetailsViewModel.plant.observeAsState().value
    if (plant != null)
        PlantDetailsScreen(
            plant,
            onMoveToGalleryClick
        )
}

@Composable
fun PlantDetailsScreen(plant: Plant, onMoveToGalleryClick: (String) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AsyncImage(
                model = plant.imageUrl,
                contentDescription = plant.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 400f
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 28.dp),
                contentAlignment = Alignment.BottomCenter

            ) {

                ClickableText(
                    text = AnnotatedString(plant.name + " gallery"),
                    style = TextStyle(color = Color.White, fontSize = 30.sp),
                    onClick = {
                        onMoveToGalleryClick(plant.name)
                    }
                )

            }
        }

    }
}
