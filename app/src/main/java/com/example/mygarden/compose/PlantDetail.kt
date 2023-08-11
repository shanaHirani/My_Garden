package com.example.mygarden.compose

import android.text.util.Linkify
import android.util.AttributeSet
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mygarden.R
import com.example.mygarden.data.model.domainModel.Plant
import com.example.mygarden.utilitis.TextSnackbarContainer
import com.example.mygarden.viewmodels.PlantDetailViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImage
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
    onShareClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val nestedScrollConnection = object : NestedScrollConnection {}
    val lazyListState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        //.nestedScroll(nestedScrollConnection),
        state = lazyListState
    ) {
        item {
            ImageShow(
                url = plant.imageUrl,
                plantName = plant.name,
                onMoveToGalleryClick = onMoveToGalleryClick
            )
        }
        item{
            PlantDescription(plant.description)
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
    onMoveToGalleryClick: (String) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(190.dp)
    ) {
        AsyncImage(
            model = url,
            contentDescription = plantName,
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp),
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
                text = AnnotatedString(plantName + " gallery"),
                style = TextStyle(color = Color.White, fontSize = 30.sp),
                onClick = {
                    onMoveToGalleryClick(plantName)
                }
            )

        }
    }
}

@Preview
@Composable
fun PlantDetailsScreenPreview() {

    PlantDetails(
        Plant(
            plantId = "malus-pumila",
            name = "Apple",
            description = "An apple is a sweet, edible fruit produced by an apple tree (Malus pumila). Apple trees are cultivated worldwide, and are the most widely grown species in the genus Malus. The tree originated in Central Asia, where its wild ancestor, Malus sieversii, is still found today. Apples have been grown for thousands of years in Asia and Europe, and were brought to North America by European colonists. Apples have religious and mythological significance in many cultures, including Norse, Greek and European Christian traditions.<br><br>Apple trees are large if grown from seed. Generally apple cultivars are propagated by grafting onto rootstocks, which control the size of the resulting tree. There are more than 7,500 known cultivars of apples, resulting in a range of desired characteristics. Different cultivars are bred for various tastes and uses, including cooking, eating raw and cider production. Trees and fruit are prone to a number of fungal, bacterial and pest problems, which can be controlled by a number of organic and non-organic means. In 2010, the fruit's genome was sequenced as part of research on disease control and selective breeding in apple production.<br><br>Worldwide production of apples in 2014 was 84.6 million tonnes, with China accounting for 48% of the total.<br><br>(From <a href=\"https://en.wikipedia.org/wiki/Apple\">Wikipedia</a>) aa An apple is a sweet, edible fruit produced by an apple tree (Malus pumila). Apple trees are cultivated worldwide, and are the most widely grown species in the genus Malus. The tree originated in Central Asia, where its wild ancestor, Malus sieversii, is still found today. Apples have been grown for thousands of years in Asia and Europe, and were brought to North America by European colonists. Apples have religious and mythological significance in many cultures, including Norse, Greek and European Christian traditions.<br><br>Apple trees are large if grown from seed. Generally apple cultivars are propagated by grafting onto rootstocks, which control the size of the resulting tree. There are more than 7,500 known cultivars of apples, resulting in a range of desired characteristics. Different cultivars are bred for various tastes and uses, including cooking, eating raw and cider production. Trees and fruit are prone to a number of fungal, bacterial and pest problems, which can be controlled by a number of organic and non-organic means. In 2010, the fruit's genome was sequenced as part of research on disease control and selective breeding in apple production.<br><br>Worldwide production of apples in 2014 was 84.6 million tonnes, with China accounting for 48% of the total.<br><br>(From <a href=\"https://en.wikipedia.org/wiki/Apple\">Wikipedia</a>)",
            growZoneNumber = 2,
            wateringInterval = 7, // how often the plant should be watered, in days
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/2/29/Beetroot_jm26647.jpg"
        ),
        false,
        {},
        {},
        {},
        {}
    )

}