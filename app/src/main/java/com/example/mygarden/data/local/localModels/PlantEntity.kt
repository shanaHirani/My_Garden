package com.example.mygarden.data.local.localModels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mygarden.data.model.domainModel.Plant
import com.example.mygarden.data.model.domainModel.PlantType

@Entity(tableName = "plants")
data class PlantEntity(
    @PrimaryKey @ColumnInfo(name = "id") val plantId: String,
    val name: String,
    val description: String,
    val plantType: String,
    val wateringInterval: Int = 7, // how often the plant should be watered, in days
    val imageUrl: String = ""
)

fun PlantEntity.asDomain(): Plant {
    return Plant(
        plantId = this.plantId,
        name = this.name,
        description = this.description,
        plantType = when (this.plantType) {
            PlantType.Tree.typeId -> PlantType.Tree
            PlantType.InDoor.typeId -> PlantType.InDoor
            PlantType.OutDoor.typeId -> PlantType.OutDoor
            else ->
                PlantType.NoType
        },
        wateringInterval = this.wateringInterval,
        imageUrl = this.imageUrl
    )
}


