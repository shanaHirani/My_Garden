package com.example.mygarden.compose.gallery


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.mygarden.compose.home.ImageListItem
import com.example.mygarden.data.model.domainModel.PlantPhoto
import com.example.mygarden.viewmodels.GalleryViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun GalleryScreen(
    viewModel: GalleryViewModel = hiltViewModel(),
    onUpClick: () -> Unit
) {
    GalleryScreen(
        plantPictures = viewModel.plantPhotoPagingFlow,
        plantName = viewModel.plantName,
        onUpClick = onUpClick
    )
}

@Composable
private fun GalleryScreen(
    plantPictures: Flow<PagingData<PlantPhoto>>,
    plantName: String,
    onUpClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            GalleryTopBar(
                plantName = plantName,
                onUpClick = onUpClick
            )
        },
    ){ padding ->
        val pagingItems: LazyPagingItems<PlantPhoto> = plantPictures.collectAsLazyPagingItems()
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(padding),
            contentPadding = PaddingValues(2.dp )
        ){
            items(
                count = pagingItems.itemCount,
            ){ index ->
                val photo = pagingItems[index] ?: return@items
                ImageListItem(name = "by " + photo.photographerName , imageUrl = photo.url)
            }
        }
    }
}

@Composable
private fun GalleryTopBar(
    plantName: String,
    onUpClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text("gallery of $plantName")
        },
        modifier = modifier.statusBarsPadding(),
        navigationIcon ={
            IconButton(onClick = onUpClick) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        }
    )
}

@Preview
@Composable
private fun GalleryScreenPreview(
    @PreviewParameter(GalleryScreenPreviewParamProvider::class) plantPictures: Flow<PagingData<PlantPhoto>>
) {
    GalleryScreen(plantPictures, "tomato")
}

private class GalleryScreenPreviewParamProvider :
    PreviewParameterProvider<Flow<PagingData<PlantPhoto>>> {
    override val values: Sequence<Flow<PagingData<PlantPhoto>>> =
        sequenceOf(
            flowOf(
                PagingData.from(
                    listOf(
                        PlantPhoto(
                            id = "1",
                            url = "https://images.unsplash.com/photo-1607305387299-a3d9611cd469?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w0NzAzMzh8MHwxfHNlYXJjaHwxfHx0b21hdG98ZW58MHx8fHwxNjkwMjQ5NDg1fDA&ixlib=rb-4.0.3&q=80&w=400",
                            photographerName = "John Smith",
                            photographerUsername = "John@Smith"
                        ),
                        PlantPhoto(
                            id = "2",
                            url = "https://images.unsplash.com/photo-1582284540020-8acbe03f4924?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w0NzAzMzh8MHwxfHNlYXJjaHwyfHx0b21hdG98ZW58MHx8fHwxNjkwMjQ5NDg1fDA&ixlib=rb-4.0.3&q=80&w=400",
                            photographerName = "Sally Smith",
                            photographerUsername = "Sally__Smith"
                        ),
                        PlantPhoto(
                            id = "3",
                            url = "https://images.unsplash.com/photo-1564665759044-959473395029?ixlib=rb-4.0.3&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max",
                            photographerName = "shana hirani",
                            photographerUsername = "John@Smith"
                        ),
                        PlantPhoto(
                            id = "4",
                            url = "https://images.unsplash.com/photo-1557800636-894a64c1696f?ixlib=rb-4.0.3&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max",
                            photographerName = "lyla boz",
                            photographerUsername = "John@Smith"
                        ),
                    )
                )
            )
        )
}
