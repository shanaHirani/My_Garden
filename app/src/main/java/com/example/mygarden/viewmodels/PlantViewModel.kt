package com.example.mygarden.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.mygarden.data.model.domainModel.Plant
import com.example.mygarden.data.model.domainModel.PlantType
import com.example.mygarden.data.repository.PlantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class PlantViewModel @Inject constructor(
    plantRepository: PlantRepository
) : ViewModel() {

    private val plantType: MutableStateFlow<PlantType> = MutableStateFlow(PlantType.NoType)
    private val isFiltered: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val plants: LiveData<List<Plant>> = plantType.flatMapLatest { plantType ->
        if (plantType == PlantType.NoType) {
            plantRepository.getPlants()
        } else {
            plantRepository.getPlantsWithPlatType(plantType)
        }
    }.asLiveData()

    fun onPlantTypeChose(chosenPlantType: PlantType) {
        plantType.value = chosenPlantType
        isFiltered.value = true
    }

    fun onUnFiltered() {
        plantType.value = PlantType.NoType
        isFiltered.value = false
    }
}
