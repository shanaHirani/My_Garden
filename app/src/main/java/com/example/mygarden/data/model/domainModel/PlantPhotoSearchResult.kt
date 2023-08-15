package com.example.mygarden.data.model.domainModel


data class PlantPhotoSearchResult(
    val plants: List<PlantPhoto>,
    val totalPages: Int,
    val total: Int
)
