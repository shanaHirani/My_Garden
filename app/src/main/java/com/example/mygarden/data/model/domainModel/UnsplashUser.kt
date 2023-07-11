package com.example.mygarden.data.model.domainModel

import com.squareup.moshi.Json

data class UnsplashUser(
    val name: String,
    val username: String
) {
    val attributionUrl: String
        get() {
            return "https://unsplash.com/$username?utm_source=sunflower&utm_medium=referral"
        }
}