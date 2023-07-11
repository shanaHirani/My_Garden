package com.example.mygarden.data.model.remoteModel

import com.example.mygarden.data.model.domainModel.UnsplashPhotoUrls
import com.example.mygarden.data.model.domainModel.UnsplashUser
import com.squareup.moshi.Json

data class UnsplashPhotoUrlsDto(
    @Json(name ="small") val small:String
)

fun UnsplashPhotoUrlsDto.asDomain(): UnsplashPhotoUrls {
    return UnsplashPhotoUrls(
        small = this.small
    )
}