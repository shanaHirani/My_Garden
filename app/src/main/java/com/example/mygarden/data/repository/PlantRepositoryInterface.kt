package com.example.mygarden.data.repository

import com.example.mygarden.data.model.Plant.PlantSearchResult
import com.example.mygarden.shared.NetworkResponse

interface PlantRepositoryInterface {
    suspend fun getPlant(): NetworkResponse<PlantSearchResult>
}