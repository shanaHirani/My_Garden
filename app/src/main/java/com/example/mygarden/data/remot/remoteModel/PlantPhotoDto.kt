package com.example.mygarden.data.remot.remoteModel

import android.util.Log
import com.example.mygarden.data.local.localModels.PlantPhotoEntity
import com.example.mygarden.data.model.domainModel.PlantPhoto
import com.squareup.moshi.Json

data class PlantPhotoDto(
    @Json(name ="id") val id: String,
    @Json(name ="urls") val urls: UnsplashPhotoUrlsDto,
    @Json(name ="user") val user: UnsplashUserDto
)

fun PlantPhotoDto.asDomain(): PlantPhoto {
    Log.i("show plant photo dto", this.toString())
    return PlantPhoto(
        id = this.id,
        url = this.urls.small,
        photographerName = this.user.name,
        photographerUsername = this.user.username
    )
}

fun PlantPhotoDto.asEntity(): PlantPhotoEntity {
    return PlantPhotoEntity(
        id = this.id,
        url = this.urls.small,
        photographerName = this.user.name,
        photographerUsername = this.user.username
    )
}