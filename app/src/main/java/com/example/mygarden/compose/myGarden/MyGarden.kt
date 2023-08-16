package com.example.mygarden.compose.myGarden

import androidx.activity.compose.ReportDrawnWhen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mygarden.R
import com.example.mygarden.data.local.localModels.GardenPlanting
import com.example.mygarden.data.local.localModels.PlantAndGardenPlantings
import com.example.mygarden.data.local.localModels.PlantEntity
import com.example.mygarden.data.local.localModels.asDomain
import com.example.mygarden.viewmodels.GardenPlantingListViewModel
import java.util.Calendar


@Composable
fun MyGardenScreen(
    modifier: Modifier = Modifier,
    viewModel: GardenPlantingListViewModel = hiltViewModel(),
    onPlantClick: (PlantAndGardenPlantings) -> Unit = {}
) {
    val gardenPlants by viewModel.plantAndGardenPlantings.collectAsState(initial = emptyList())
    GardenScreen(
        gardenPlants = gardenPlants,
        modifier = modifier,
        onPlantClick = onPlantClick
    )
}

@Composable
fun GardenScreen(
    gardenPlants: List<PlantAndGardenPlantings>,
    modifier: Modifier = Modifier,
    onAddPlantClick: () -> Unit = {},
    onPlantClick: (PlantAndGardenPlantings) -> Unit = {}
) {
    if (gardenPlants.isEmpty()) {
        EmptyGarden(onAddPlantClick, modifier)
    } else {
        GardenList(gardenPlants = gardenPlants, onPlantClick = onPlantClick, modifier = modifier)
    }
}

@Composable
private fun GardenList(
    gardenPlants: List<PlantAndGardenPlantings>,
    onPlantClick: (PlantAndGardenPlantings) -> Unit,
    modifier: Modifier = Modifier,
) {
    // Call reportFullyDrawn when the garden list has been rendered
    val gridState = rememberLazyGridState()
    ReportDrawnWhen { gridState.layoutInfo.totalItemsCount > 0 }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier,
        state = gridState,
        contentPadding = PaddingValues(
            horizontal = dimensionResource(id = R.dimen.card_side_margin),
            vertical = dimensionResource(id = R.dimen.margin_normal)
        )
    ) {
        items(
            items = gardenPlants,
            key = { it.plantEntity.asDomain().plantId }
        ) {
            MyGardenItem(
                it,
                onPlantClick
            )
        }
    }
}


@Composable
private fun EmptyGarden(onAddPlantClick: () -> Unit, modifier: Modifier = Modifier) {
    Text(text = "empty garden")

}

@Preview
@Composable
private fun GardenScreenPreview(
    @PreviewParameter(GardenScreenPreviewParamProvider::class) gardenPlants: List<PlantAndGardenPlantings>
) {
    GardenScreen(gardenPlants)
}

private class GardenScreenPreviewParamProvider :
    PreviewParameterProvider<List<PlantAndGardenPlantings>> {
    override val values: Sequence<List<PlantAndGardenPlantings>> =
        sequenceOf(
            emptyList(),
            listOf(
                PlantAndGardenPlantings(
                    plantEntity = PlantEntity(
                        plantId = "1",
                        name = "Apple",
                        description = "An apple.",
                        plantType= "tree",
                        wateringInterval = 2,
                        imageUrl = "https://images.unsplash.com/photo-1417325384643-aac51acc9e5d?q=75&fm=jpg&w=400&fit=max",
                    ),
                    gardenPlantings = listOf(
                        GardenPlanting(
                            plantId = "1",
                            plantDate = Calendar.getInstance(),
                            lastWateringDate = Calendar.getInstance()
                        )
                    )
                )
            )
        )
}