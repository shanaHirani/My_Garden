package com.example.mygarden.data.model.domainModel


data class PlantSearchResult(
    val plants: List<PlantPhoto>,
    val totalPages: Int,
    val total: Int
)
