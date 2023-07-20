package com.example.mygarden

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mygarden.compose.plantList.PlantList
import com.example.mygarden.data.model.domainModel.Plant
import com.example.mygarden.ui.theme.MyGardenTheme
import com.example.mygarden.viewmodels.GalleryViewModel
import com.example.mygarden.viewmodels.PlantViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity() : ComponentActivity() {

    private val galleryViewModel : GalleryViewModel by viewModels()
    private val plantViewModel : PlantViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyGardenTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   /* val plantPhoto = galleryViewModel.plantPhotoPagingFlow.collectAsLazyPagingItems()
                    GalleryScreen(plantPhoto)*/
                   /* var plants = plantViewModel.Plants.collectAsState(listOf()).value.toString()
                    Greeting(plants)*/
                    PlantList()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
