package com.example.mygarden.data.model.Plant

data class PlantSearchResult(
    val results: List<Result>,
    val total: Int,
    val total_pages: Int
)