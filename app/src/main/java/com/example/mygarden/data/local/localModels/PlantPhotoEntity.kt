package com.example.mygarden.data.local.localModels

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mygarden.data.model.domainModel.PlantPhoto

@Entity
data class PlantPhotoEntity(
    @PrimaryKey
    val id: String,
    val url: String,
    val photographerName: String,
    val photographerUsername: String
)

fun PlantPhotoEntity.asDomain(): PlantPhoto {
    return PlantPhoto(
        id = this.id,
        url = this.url,
        photographerName = this.photographerName,
        photographerUsername = this.photographerUsername
    )
}