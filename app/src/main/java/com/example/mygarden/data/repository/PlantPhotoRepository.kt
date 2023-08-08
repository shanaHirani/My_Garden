package com.example.mygarden.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.mygarden.api.PlantService
import com.example.mygarden.data.model.domainModel.PlantPhoto
import com.example.mygarden.data.remot.PlantPhotoPagingSource
import com.example.mygarden.data.remot.remoteModel.asDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlantPhotoRepository  @Inject constructor(
    private val plantService: PlantService
) {
    fun getPlantPhotosStream(query: String): Flow<PagingData<PlantPhoto>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 25),
            pagingSourceFactory = { PlantPhotoPagingSource(plantService, query) }
        ).flow.map { pagingData->
            pagingData.map { it ->
                it.asDomain()
            }
        }
    }
}