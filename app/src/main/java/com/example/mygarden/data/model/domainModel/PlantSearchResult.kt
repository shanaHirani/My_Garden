package com.example.mygarden.data.model.domainModel

import com.example.mygarden.data.remot.remoteModel.PlantPhotoDto
import com.squareup.moshi.Json

data class PlantSearchResult(
    val plants: List<PlantPhoto>,
    val totalPages: Int,
    val total: Int
)
