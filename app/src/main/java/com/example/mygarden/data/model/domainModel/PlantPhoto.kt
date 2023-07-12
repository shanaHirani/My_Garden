package com.example.mygarden.data.model.domainModel

import com.example.mygarden.data.remot.remoteModel.UnsplashPhotoUrlsDto
import com.example.mygarden.data.remot.remoteModel.UnsplashUserDto
import com.squareup.moshi.Json

data class PlantPhoto(
    val id: String,
    val urls: UnsplashPhotoUrls,
    val user: UnsplashUser
)