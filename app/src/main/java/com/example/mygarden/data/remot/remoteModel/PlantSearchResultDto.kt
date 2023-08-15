package com.example.mygarden.data.remot.remoteModel

import com.example.mygarden.data.model.domainModel.PlantPhoto
import com.example.mygarden.data.model.domainModel.PlantPhotoSearchResult
import com.squareup.moshi.Json

data class PlantSearchResultDto(
    @Json(name = "results") val plants: List<PlantPhotoDto>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total")val total: Int
)

fun PlantSearchResultDto.asDomain(): PlantPhotoSearchResult {
    return PlantPhotoSearchResult(
        plants = this.plants.asDomain(),
        totalPages = this.totalPages,
        total = this.total
    )
}

fun List<PlantPhotoDto>.asDomain():List<PlantPhoto>{
    return mapNotNull {
        it.asDomain()
    }
}