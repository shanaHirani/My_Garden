package com.example.mygarden.viewmodels


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import androidx.paging.cachedIn
import com.example.mygarden.data.repository.PlantPhotoRepository
import com.example.mygarden.feature.Plant_Name
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject



@HiltViewModel
class GalleryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    plantPhotoRepository: PlantPhotoRepository
) : ViewModel() {

    private var queryString: String? = savedStateHandle["$Plant_Name"]

    var plantName = queryString ?: "Tulip"
    init{
        if(plantName.equals("{$Plant_Name}")) {
            plantName = "Tulip"
        }
    }

    val plantPhotoPagingFlow = plantPhotoRepository
        .getPlantPhotosStream(plantName)
        .cachedIn(viewModelScope)
}