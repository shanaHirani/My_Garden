package com.example.mygarden.data.repository

import com.example.mygarden.data.local.Daos.PlantDao
import com.example.mygarden.data.local.localModels.asDomain
import com.example.mygarden.data.model.domainModel.PlantType
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlantRepository @Inject constructor(private val plantDao: PlantDao) {

    fun getPlants() = plantDao.getPlants().map {
        it.mapNotNull {plantEntity->
            plantEntity.asDomain()
        }
    }

    fun getPlant(plantId: String) = plantDao.getPlant(plantId).map{
        it.asDomain()
    }

    fun getPlantsWithPlatType(plantType:PlantType) =
        plantDao.getPlantsWithPlantType(plantType.typeId).map {
            it.mapNotNull { plantEntity ->
                plantEntity.asDomain()
            }
        }
}