package com.example.mygarden.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.mygarden.data.repository.PlantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PlantDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    plantRepository: PlantRepository
):ViewModel(){

    val plantId: String = savedStateHandle.get<String>(PLANT_ID_SAVED_STATE_KEY)!!
    val plant = plantRepository.getPlant(plantId).asLiveData()

    companion object{
        private const val PLANT_ID_SAVED_STATE_KEY = "plantId"
    }
}