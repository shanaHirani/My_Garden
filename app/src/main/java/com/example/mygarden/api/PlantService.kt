package com.example.mygarden.api

import com.example.mygarden.data.remot.remoteModel.PlantSearchResultDto
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


//permission to access internet
//android:usesCleartextTraffic="true"
//nullable variable
interface PlantService {

    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") query:String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): PlantSearchResultDto


    companion object {


        private const val ACCESS_KEY = "6oPM7YzfIicsFCIJsQ5XByofFQhDkt_0nhlt7NrRPSU"
        private const val BASE_URL = "https://api.unsplash.com/"
        fun create():PlantService{
            val logger = HttpLoggingInterceptor().apply { level = Level.BODY }
            //query parram
            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url
                    .newBuilder()
                    .addQueryParameter("client_id", ACCESS_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            val client = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
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