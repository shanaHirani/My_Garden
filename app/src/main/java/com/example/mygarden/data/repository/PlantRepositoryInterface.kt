package com.example.mygarden.data.repository

import com.example.mygarden.data.model.domainModel.PlantSearchResult
import com.example.mygarden.data.remot.remoteModel.PlantSearchResultDto
import com.example.mygarden.shared.NetworkResponse

interface PlantRepositoryInterface {
    suspend fun getPlant(): NetworkResponse<PlantSearchResult>
}