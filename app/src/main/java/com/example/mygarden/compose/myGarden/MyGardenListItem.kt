package com.example.mygarden.compose.myGarden

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mygarden.R
import com.example.mygarden.data.local.localModels.PlantAndGardenPlantings
import com.example.mygarden.ui.theme.FertilizerIcon
import com.example.mygarden.ui.theme.InsecticideIcon
import com.example.mygarden.ui.theme.PlantItemShadow
import com.example.mygarden.ui.theme.UnNeededIcon
import com.example.mygarden.ui.theme.WateringIcon
import com.example.mygarden.viewmodels.PlantAndGardenPlantingsViewModel

enum class PlantNeeds(
    val iconDrawableId :Int,
    val unNeededTintColor:Color = UnNeededIcon,
    val neededTintColor: Color,
    val contentDescription:String
){
    WATERING(
        R.drawable.ic_sprinkler,
        neededTintColor = WateringIcon,
        contentDescription="watering"
    ),
    FERTILIZER(
        R.drawable.ic_fertilizer,
        neededTintColor = FertilizerIcon,
        contentDescription="fertilizer"
    ),
    INSECTICIDE(
        R.drawable.ic_insecticide,
        neededTintColor = InsecticideIcon,
        contentDescription="insecticide"
    )
}
@Composable
fun MyGardenItem(
    plantAndGardenPlantings: PlantAndGardenPlantings,
    onPlantClick: (plant: PlantAndGardenPlantings) -> Unit = {}
) {
    val mv = PlantAndGardenPlantingsViewModel(plantAndGardenPlantings)
    MyGardenItem(
        mv.isWateringNeed,
        mv.isFertilizerNeed,
        mv.isInsecticideNeed,
        mv.plantName,
        mv.imageUrl,
        onPlantClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyGardenItem(
    isWateringNeed: Boolean,
    isFertilizerNeed: Boolean,
    isInsecticideNeed: Boolean,
    plantName:String,
    imageUrl:String,
    onPlantClick: (plant: PlantAndGardenPlantings) -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(15.dp),
        onClick = { onPlantClick },
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.card_margin)),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.plant_item_image_height))
                .background(color = PlantItemShadow),
            contentAlignment = Alignment.BottomCenter

        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = plantName,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                PlantItemShadow
                            ),
                            startY = 200f
                        )
                    )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                PlantNeedIcon(PlantNeeds.WATERING,isWateringNeed)
                PlantNeedIcon(PlantNeeds.FERTILIZER,isFertilizerNeed)
                PlantNeedIcon(PlantNeeds.INSECTICIDE,isInsecticideNeed)
            }
        }

    }
}

@Composable
fun PlantNeedIcon(
    plantNeeds: PlantNeeds,
    isNeeded:Boolean
){
    var tintColor = plantNeeds.unNeededTintColor
    var iconSize = dimensionResource(id = R.dimen.un_plant_needed_icon_size)
    if(isNeeded){
        tintColor = plantNeeds.neededTintColor
        var iconSize = dimensionResource(id = R.dimen.plant_needed_icon_size)
    }
    Icon(
        painterResource(id = plantNeeds.iconDrawableId),
        tint = tintColor,
        contentDescription = plantNeeds.name,
        modifier = Modifier.size(iconSize)
    )
}

@Preview
@Composable
fun MyGardenItemPreview() {
    Card(modifier = Modifier.size(180.dp,190.dp)) {
        MyGardenItem(
            true,
            false,
            false,
            "tomato",
            "https://upload.wikimedia.org/wikipedia/commons/2/29/Beetroot_jm26647.jpg",
        )
    }
}

