package com.example.mygarden.data.remot.remoteModel

import com.squareup.moshi.Json

data class UnsplashPhotoUrlsDto(
    @Json(name ="small") val small:String
)
