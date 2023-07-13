package com.example.mygarden.data.remot.remoteModel

import com.squareup.moshi.Json

data class UnsplashUserDto(
    @Json(name = "name") val name: String,
    @Json(name = "username") val username: String
)
