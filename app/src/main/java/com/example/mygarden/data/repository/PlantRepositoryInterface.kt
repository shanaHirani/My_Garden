package com.example.mygarden.data.repository

import com.example.mygarden.data.model.domainModel.PlantSearchResult
import com.example.mygarden.shared.NetworkResponse

interface PlantRepositoryInterface {
    suspend fun getPlant(plantName :String): NetworkResponse<PlantSearchResult>
}