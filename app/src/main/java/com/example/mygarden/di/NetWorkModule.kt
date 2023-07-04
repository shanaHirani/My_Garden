package com.example.mygarden.di

import com.example.mygarden.api.PlantService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetWorkModule {

    @Singleton
    @Provides
    fun providePlantService():PlantService{
        return PlantService.create()
    }
}