package com.example.mygarden.compose.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mygarden.R
import com.example.mygarden.data.model.domainModel.Plant
import com.example.mygarden.ui.theme.LightGreen
import com.example.mygarden.viewmodels.PlantViewModel
import androidx.compose.material.Icon
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.mygarden.data.model.domainModel.PlantType
import com.example.mygarden.ui.theme.TopBarDarkGreen

@Composable
fun HomeScreen(
    onPlantClick: (Plant) -> Unit,
    modifier: Modifier = Modifier.background(LightGreen),
    viewModel: PlantViewModel = hiltViewModel()
) {
    val plants by viewModel.plants.observeAsState(initial = emptyList())
    HomeScreen(
        plants = plants,
        modifier,
        onPlantClick = onPlantClick,
        onPlantTypeChose = { viewModel.onPlantTypeChose(it) },
        onUnFiltered = { viewModel.onUnFiltered() }
    )
}

@Composable
fun HomeScreen(
    plants: List<Plant>,
    modifier: Modifier = Modifier,
    onPlantClick: (Plant) -> Unit = {},
    onPlantTypeChose: (PlantType) -> Unit = {},
    onUnFiltered: () -> Unit = {}

) {
    Column(
        modifier = Modifier
            .background(LightGreen)
            .padding(0.dp, 0.dp, 0.dp, dimensionResource(id = R.dimen.bottom_menu_height))
    ) {
        Title(
            onPlantTypeChose = onPlantTypeChose,
            onUnFiltered = onUnFiltered
        )
        PlantList(plants = plants, modifier, onPlantClick = onPlantClick)
    }
}

@Composable
fun Title(
    onPlantTypeChose: (chosenPlantType: PlantType) -> Unit = {},
    onUnFiltered: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(LightGreen)
            .padding(
                horizontal = dimensionResource(id = R.dimen.title_horizontal_margin),
                vertical = dimensionResource(id = R.dimen.title_vertical_margin)
            ),
        shape = RoundedCornerShape(15.dp),

        ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.title_height))
                .background(TopBarDarkGreen)
                .padding(15.dp)

        ) {
            Box(
                modifier = Modifier.weight(11f)
            ) {
                Text(
                    text = stringResource(id = R.string.title),
                    style = TextStyle(color = Color.White, fontSize = 18.sp)
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Demo_DropDownMenu(
                    onPlantTypeChose = onPlantTypeChose,
                    onUnFiltered = onUnFiltered
                )
            }
        }
    }
}

@Composable
fun Demo_DropDownMenu(
    onPlantTypeChose: (chosenPlantType: PlantType) -> Unit,
    onUnFiltered: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        IconButton(onClick = {
            expanded = !expanded
            onUnFiltered()
        }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = "filter",
                tint = Color.Green,
                modifier = Modifier.size(30.dp)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            FilterMenuItem(PlantType.Tree) { onPlantTypeChose(PlantType.Tree) }
            FilterMenuItem(PlantType.InDoor){ onPlantTypeChose(PlantType.InDoor) }
            FilterMenuItem(PlantType.OutDoor){ onPlantTypeChose(PlantType.OutDoor) }
        }
    }
}

@Composable
fun FilterMenuItem(
    plantType: PlantType,
    onPlantTypeChose: () -> Unit
) {
    DropdownMenuItem(
        text = { Text(plantType.typename) },
        leadingIcon = {
            Icon(
                painterResource(id = plantType.iconDrawableId),
                contentDescription = plantType.typename,
                modifier = Modifier.size(dimensionResource(id = R.dimen.filter_menu_icon_size))
            )
        },
        onClick = {onPlantTypeChose()}
    )
}


@Composable
fun PlantList(
    plants: List<Plant>,
    modifier: Modifier = Modifier,
    onPlantClick: (Plant) -> Unit = {},
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.testTag("plant_list"),
        contentPadding = PaddingValues(
            horizontal = dimensionResource(id = R.dimen.list_side_margin),
            vertical = dimensionResource(id = R.dimen.list_header_margin)
        )
    ) {
        items(
            plants.size
        ) { it ->
            PlantItem(plant = plants[it]) {
                onPlantClick(plants[it])
            }
        }
    }
}