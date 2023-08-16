package com.example.mygarden.data.local.localModels

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mygarden.data.model.domainModel.Plant

data class PlantAndGardenPlantings(
    @Embedded
    val plantEntity: PlantEntity,

    @Relation(parentColumn = "id", entityColumn = "plant_id")
    val gardenPlantings: List<GardenPlanting> = emptyList()
)