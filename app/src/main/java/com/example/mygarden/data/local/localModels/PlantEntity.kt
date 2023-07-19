package com.example.mygarden.data.local.localModels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mygarden.data.model.domainModel.Plant
import com.example.mygarden.data.model.domainModel.PlantPhoto
import java.util.Calendar

@Entity(tableName = "plants")
data class PlantEntity(
    @PrimaryKey @ColumnInfo(name = "id") val plantId: String,
    val name: String,
    val description: String,
    val growZoneNumber: Int,
    val wateringInterval: Int = 7, // how often the plant should be watered, in days
    val imageUrl: String = ""
)

fun PlantEntity.asDomain(): Plant {
    return Plant(
        plantId = this.plantId,
        name = this.name,
        description = this.description,
        growZoneNumber = this.growZoneNumber,
        wateringInterval = this.wateringInterval,
        imageUrl = this.imageUrl
    )
}


