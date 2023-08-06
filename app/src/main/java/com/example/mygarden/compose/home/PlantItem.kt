package com.example.mygarden.compose.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mygarden.R
import com.example.mygarden.data.model.domainModel.Plant


@Composable
fun PlantItem(plant: Plant, onClick: () -> Unit = {}) {
    ImageListItem(name = plant.name, imageUrl = plant.imageUrl, onClick = onClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageListItem(name: String, imageUrl: String, onClick: () -> Unit = {}) {
Card(
    modifier = Modifier.fillMaxSize()
        .padding(dimensionResource(id = R.dimen.card_margin))
){
        Card(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.plant_item_image_height))

            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Box(modifier = Modifier.fillMaxSize()
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
                        .padding(all = 8.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Text(name, style = TextStyle(color = Color.White, fontSize = 18.sp))
                }
            }

        }
    }
}
@Preview
@Composable
fun PlantItemPreview() {
    Card(modifier = Modifier.fillMaxSize().offset(10.dp,10.dp)) {
        PlantItem(
            Plant(
                plantId = "malus-pumila",
                name = "Apple",
                description = "An apple is a sweet, edible fruit produced by an apple tree (Malus pumila). Apple trees are cultivated worldwide, and are the most widely grown species in the genus Malus. The tree originated in Central Asia, where its wild ancestor, Malus sieversii, is still found today. Apples have been grown for thousands of years in Asia and Europe, and were brought to North America by European colonists. Apples have religious and mythological significance in many cultures, including Norse, Greek and European Christian traditions.<br><br>Apple trees are large if grown from seed. Generally apple cultivars are propagated by grafting onto rootstocks, which control the size of the resulting tree. There are more than 7,500 known cultivars of apples, resulting in a range of desired characteristics. Different cultivars are bred for various tastes and uses, including cooking, eating raw and cider production. Trees and fruit are prone to a number of fungal, bacterial and pest problems, which can be controlled by a number of organic and non-organic means. In 2010, the fruit's genome was sequenced as part of research on disease control and selective breeding in apple production.<br><br>Worldwide production of apples in 2014 was 84.6 million tonnes, with China accounting for 48% of the total.<br><br>(From <a href=\"https://en.wikipedia.org/wiki/Apple\">Wikipedia</a>)",
                growZoneNumber = 2,
                wateringInterval = 7, // how often the plant should be watered, in days
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/2/29/Beetroot_jm26647.jpg"
            )
        )
    }
}