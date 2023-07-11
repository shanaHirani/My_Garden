package com.example.mygarden.data.repository

import com.example.mygarden.data.model.domainModel.PlantSearchResult
import com.example.mygarden.data.model.remoteModel.PlantSearchResultDto
import com.example.mygarden.data.model.remoteModel.asDomain
import com.example.mygarden.data.remot.RemoteDataSource
import com.example.mygarden.shared.NetworkResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlantRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
):PlantRepositoryInterface,BaseRepository() {
    override suspend fun getPlant(): NetworkResponse<PlantSearchResult> =
        safeApiCall(
            {remoteDataSource.getPlant()},
            {it.asDomain()}
        )


}