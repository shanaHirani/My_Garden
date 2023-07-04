package com.example.mygarden.api

import com.example.mygarden.data.PlantSearchResult
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


//permission to access internet
//android:usesCleartextTraffic="true"
//nullable variable
interface PlantService {

    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("client_id") clientId: String = ACCESS_KEY
    ): PlantSearchResult


    companion object {

        // SECRET KEY = sObeA4gbNMt7C238uLvn-YaxyRb8UarrvoWH-pZPXA
        private const val ACCESS_KEY = "6oPM7YzfIicsFCIJsQ5XByofFQhDkt_0nhlt7NrRPSU"
        private const val BASE_URL = "https://api.unsplash.com/"
        fun create():PlantService{
            val logger = HttpLoggingInterceptor().apply { level = Level.BODY }

            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(PlantService::class.java)
        }
    }
}