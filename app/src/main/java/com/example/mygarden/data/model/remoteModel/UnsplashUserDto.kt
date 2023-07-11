package com.example.mygarden.data.model.remoteModel

import com.example.mygarden.data.model.domainModel.UnsplashUser
import com.squareup.moshi.Json

data class UnsplashUserDto(
    @Json(name = "name") val name: String,
    @Json(name = "username") val username: String
)

fun UnsplashUserDto.asDomain(): UnsplashUser {
    return UnsplashUser(
        name = this.name,
        username = this.username
    )
}