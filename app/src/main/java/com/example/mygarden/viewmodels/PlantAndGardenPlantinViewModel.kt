package com.example.mygarden.viewmodels

import com.example.mygarden.data.local.localModels.PlantAndGardenPlantings
import com.example.mygarden.data.local.localModels.asDomain
import java.text.SimpleDateFormat
import java.util.Locale

class PlantAndGardenPlantingsViewModel(plantings: PlantAndGardenPlantings) {
    private val plant = checkNotNull(plantings.plantEntity.asDomain())
    private val gardenPlanting = plantings.gardenPlantings[0]

    val isWateringNeed : Boolean = false
    val isFertilizerNeed: Boolean = false
    val isInsecticideNeed: Boolean = false

    val waterDateString: String = dateFormat.format(gardenPlanting.lastWateringDate.time)
    val wateringInterval
        get() = plant.wateringInterval
    val imageUrl
        get() = plant.imageUrl
    val plantName
        get() = plant.name
    val plantDateString: String = dateFormat.format(gardenPlanting.plantDate.time)
    val plantId
        get() = plant.plantId

    companion object {
        private val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.US)
    }
}