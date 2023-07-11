package com.example.mygarden.data.model.remoteModel

import com.example.mygarden.data.model.domainModel.PlantPhoto
import com.example.mygarden.data.model.domainModel.UnsplashUser
import com.squareup.moshi.Json

data class PlantPhotoDto(
    @Json(name ="id") val id: String,
    @Json(name ="urls") val urls: UnsplashPhotoUrlsDto,
    @Json(name ="user") val user: UnsplashUserDto
)

fun PlantPhotoDto.asDomain(): PlantPhoto {
    return PlantPhoto(
        id = this.id,
        urls = this.urls.asDomain(),
        user = this.user.asDomain()
    )
}
