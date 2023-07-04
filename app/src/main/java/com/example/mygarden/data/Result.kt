package com.example.mygarden.data

import com.google.gson.annotations.SerializedName

data class Result(
    val blur_hash: String,
    val color: String,
    val created_at: String,
    @field:SerializedName("current_user_collections") val currentUserCollections: List<Any>,
    val description: String,
    val height: Int,
    val id: String,
    @field:SerializedName("liked_by_user") val likedByUser: Boolean,
    val likes: Int,
    val links: Links,
    val urls: Urls,
    val user: User,
    val width: Int
)