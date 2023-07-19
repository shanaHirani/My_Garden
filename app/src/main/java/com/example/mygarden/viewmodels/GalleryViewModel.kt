package com.example.mygarden.viewmodels


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mygarden.data.model.domainModel.PlantPhoto
import com.example.mygarden.data.model.domainModel.PlantSearchResult
import com.example.mygarden.data.repository.PlantPhotoRepository
import com.example.mygarden.data.repository.PlantRepository
import com.example.mygarden.shared.Event
import com.example.mygarden.shared.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val plantPhotoRepository: PlantPhotoRepository
) : ViewModel() {

    val plantPhotoPagingFlow = plantPhotoRepository
        .getPlantPhotosStream("tomato")
        .cachedIn(viewModelScope)
}