package com.example.mygarden.data.remot

import com.example.mygarden.api.PlantService
import com.example.mygarden.data.remot.remoteModel.PlantSearchResultDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val plantService:PlantService
) {
        suspend fun getPlant(): PlantSearchResultDto {
            return plantService.searchPhotos( "''",1,10)
        }
}