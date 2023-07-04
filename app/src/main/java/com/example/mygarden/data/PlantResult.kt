package com.example.mygarden.data

import com.google.gson.annotations.SerializedName

data class PlantSearchResult(
    //val results: List<Result>,
    //val total: Int,
    @field:SerializedName("total_pages") val totalPage: Int?
)