package com.example.mygarden.compose

import android.text.util.Linkify
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mygarden.R
import com.example.mygarden.data.model.domainModel.Plant
import com.example.mygarden.utilitis.TextSnackbarContainer
import com.example.mygarden.viewmodels.PlantDetailViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImage
import com.example.mygarden.ui.theme.PenDarkGreen
import com.example.mygarden.ui.theme.PlantDetailFloatingActionButton
import com.example.mygarden.ui.theme.TopBarDarkGreen
import com.google.android.material.textview.MaterialTextView

@Composable
fun PlantDetailScreen(
    plantDetailsViewModel: PlantDetailViewModel = hiltViewModel(),
    onMoveToGalleryClick: (String) -> Unit,
    onBackClick: () -> Unit,
    onShareClick: (String) -> Unit,
) {
    val plant = plantDetailsViewModel.plant.observeAsState().value
    val isPlanted = plantDetailsViewModel.isPlanted.collectAsState(initial = false).value
    val showSnackbar = plantDetailsViewModel.showSnackbar.observeAsState().value

    if (plant != null && isPlanted != null && showSnackbar != null)
        Surface {
            TextSnackbarContainer(
                snackbarText = stringResource(R.string.added_plant_to_garden),
                showSnackbar = showSnackbar,
                onDismissSnackbar = { plantDetailsViewModel.dismissSnackbar() }
            ) {
                PlantDetails(
                    plant = plant,
                    isPlanted = isPlanted,
                    onMoveToGalleryClick = onMoveToGalleryClick,
                    onBackClick = onBackClick,
                    onShareClick = onShareClick,
                    onFabClick = { plantDetailsViewModel.addPlantToGarden() },
                )
            }
        }
}


@Composable
fun PlantDetails(
    plant: Plant,
    isPlanted: Boolean,
    onMoveToGalleryClick: (String) -> Unit = {},
    onBackClick: () -> Unit,
    onFabClick: () -> Unit,
    onShareClick: (String) -> Unit
) {
    Scaffold(
        topBar = {
            PlantDetailTopBar(
                onBackClick = onBackClick,
                onShareClick = onShareClick,
                onMoveToGalleryClick = onMoveToGalleryClick,
                plantName = plant.name,
                plantDestination = plant.description
            )
        }
    ) {
        val lazyListState = rememberLazyListState()
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
            state = lazyListState
        ) {
            item {
                ImageShow(
                    url = plant.imageUrl,
                    plantName = plant.name,
                )
            }
            item {
                PlantInformation(
                    name = plant.name,
                    wateringInterval = plant.wateringInterval,
                    description = plant.description
                )
            }
        }
    }
    if (!isPlanted)
        Fab(onFabClick)
}

@Composable
private fun PlantInformation(
    name: String,
    wateringInterval: Int,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(8.dp)) {
        Text(
            text = name,
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.CenterHorizontally)
        )
        Box(
            Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp)
        ) {
            Column(Modifier.fillMaxWidth()) {
                Text(
                    text = "watering need",
                    color = PenDarkGreen,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(4.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = pluralStringResource(
                        R.plurals.watering_needs_suffix,
                        wateringInterval,
                        wateringInterval
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
        PlantDescription(description)
    }
}

@Composable
private fun PlantDetailTopBar(
    onBackClick: () -> Unit,
    plantName: String,
    plantDestination: String,
    onShareClick: (String) -> Unit,
    onMoveToGalleryClick: (String) -> Unit
) {
    TopAppBar(
        backgroundColor = TopBarDarkGreen,
        contentColor = Color.White ,
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        },
        title = {
            Text(text = "$plantName details")
        },
        actions = {
            IconButton(onClick = { onShareClick(plantDestination) }) {
                Icon(painterResource(id = R.drawable.ic_share),
                    contentDescription = "share",
                    modifier = Modifier.size(dimensionResource(id = R.dimen.top_bar_icon_size))
                )
            }
            IconButton(onClick = { onMoveToGalleryClick(plantName) }) {
                Icon(painterResource(id = R.drawable.ic_gallery),
                    contentDescription = "gallery",
                    modifier = Modifier.size(dimensionResource(id = R.dimen.top_bar_icon_size)))
            }
        }
    )
}

@Composable
private fun Fab(onFabClick: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        FloatingActionButton(
            onClick = onFabClick,
            backgroundColor = PlantDetailFloatingActionButton,
            contentColor = Color.White
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "",
                modifier = Modifier.size(45.dp)
            )
        }
    }
}

@Composable
private fun PlantDescription(description: String) {
    val spannedText = HtmlCompat.fromHtml(description, 0)
    AndroidView(
        modifier = Modifier.padding(8.dp),
        factory = {
            MaterialTextView(it).apply {
                // links
                autoLinkMask = Linkify.WEB_URLS
                linksClickable = true
                setLinkTextColor(Color.Green.toArgb())
            }
        },
        update = {
            it.text = spannedText
        }
    )
}

@Composable
fun ImageShow(
    url: String,
    plantName: String,
) {
    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.plant_details_image_height)),
        model = url,
        contentDescription = plantName,
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
fun PlantDetailsScreenPreview() {

    PlantDetails(
        Plant(
            plantId = "malus-pumila",
            name = "Apple",
            description = "An apple is a sweet, edible fruit produced by an apple tree (Malus pumila). Apple trees are cultivated worldwide, and are the most widely grown species in the genus Malus. The tree originated in Central Asia, where its wild ancestor, Malus sieversii, is still found today. Apples have been grown for thousands of years in Asia and Europe, and were brought to North America by European colonists. Apples have religious and mythological significance in many cultures, including Norse, Greek and European Christian traditions.<br><br>Apple trees are large if grown from seed. Generally apple cultivars are propagated by grafting onto rootstocks, which control the size of the resulting tree. There are more than 7,500 known cultivars of apples, resulting in a range of desired characteristics. Different cultivars are bred for various tastes and uses, including cooking, eating raw and cider production. Trees and fruit are prone to a number of fungal, bacterial and pest problems, which can be controlled by a number of organic and non-organic means. In 2010, the fruit's genome was sequenced as part of research on disease control and selective breeding in apple production.<br><br>Worldwide production of apples in 2014 was 84.6 million tonnes, with China accounting for 48% of the total.<br><br>(From <a href=\"https://en.wikipedia.org/wiki/Apple\">Wikipedia</a>) aa An apple is a sweet, edible fruit produced by an apple tree (Malus pumila). Apple trees are cultivated worldwide, and are the most widely grown species in the genus Malus. The tree originated in Central Asia, where its wild ancestor, Malus sieversii, is still found today. Apples have been grown for thousands of years in Asia and Europe, and were brought to North America by European colonists. Apples have religious and mythological significance in many cultures, including Norse, Greek and European Christian traditions.<br><br>Apple trees are large if grown from seed. Generally apple cultivars are propagated by grafting onto rootstocks, which control the size of the resulting tree. There are more than 7,500 known cultivars of apples, resulting in a range of desired characteristics. Different cultivars are bred for various tastes and uses, including cooking, eating raw and cider production. Trees and fruit are prone to a number of fungal, bacterial and pest problems, which can be controlled by a number of organic and non-organic means. In 2010, the fruit's genome was sequenced as part of research on disease control and selective breeding in apple production.<br><br>Worldwide production of apples in 2014 was 84.6 million tonnes, with China accounting for 48% of the total.<br><br>(From <a href=\"https://en.wikipedia.org/wiki/Apple\">Wikipedia</a>)",
            plantType = "tree",
            wateringInterval = 8, // how often the plant should be watered, in days
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/2/29/Beetroot_jm26647.jpg"
        ),
        false,
        {},
        {},
        {},
        {}
    )

}