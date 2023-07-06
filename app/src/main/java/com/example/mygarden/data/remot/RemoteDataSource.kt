package com.example.mygarden.data.remot

import com.example.mygarden.api.PlantService
import com.example.mygarden.data.model.Plant.PlantSearchResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val plantService:PlantService
) {
        suspend fun getPlant(): PlantSearchResult {
            return plantService.searchPhotos( "''",1,10)
        }
}