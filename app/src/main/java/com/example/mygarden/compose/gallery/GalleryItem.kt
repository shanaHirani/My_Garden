package com.example.mygarden.compose.gallery

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mygarden.data.model.domainModel.PlantPhoto

@Composable
fun GalleryItem(
    plantPhoto: PlantPhoto

) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .height(190.dp),
        elevation = 4.dp
    ) {
        AsyncImage(
            model = plantPhoto.url,
            contentDescription = plantPhoto.photographerUsername,
            modifier = Modifier,
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
fun GalleryItemPreview() {
    GalleryItem(
        plantPhoto = PlantPhoto(
            id = "1",
            url = "https://images.unsplash.com/photo-1607305387299-a3d9611cd469?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w0NzAzMzh8MHwxfHNlYXJjaHwxfHx0b21hdG98ZW58MHx8fHwxNjg5MjEwMTgwfDA&ixlib=rb-4.0.3&q=80&w=400",
            photographerName = "shaghayegh",
            photographerUsername = "shana@1996"
        )
    )
}