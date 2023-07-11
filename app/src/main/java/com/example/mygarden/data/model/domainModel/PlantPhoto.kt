package com.example.mygarden.data.model.domainModel

import com.example.mygarden.data.model.remoteModel.UnsplashPhotoUrlsDto
import com.example.mygarden.data.model.remoteModel.UnsplashUserDto
import com.squareup.moshi.Json

data class PlantPhoto(
    val id: String,
    val urls: UnsplashPhotoUrls,
    val user: UnsplashUser
)