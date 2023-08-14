package com.example.mygarden.data.local.Daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mygarden.data.local.localModels.PlantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {
    @Query("SELECT * FROM plants ORDER BY name")
    fun getPlants(): Flow<List<PlantEntity>>

    @Query("SELECT * FROM plants WHERE plantType = :plantType ORDER BY name")
    fun getPlantsWithPlantType(plantType: String):Flow<List<PlantEntity>>

    @Query("SELECT * FROM plants WHERE id = :plantId")
    fun getPlant(plantId: String): Flow<PlantEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plants: List<PlantEntity>)
}