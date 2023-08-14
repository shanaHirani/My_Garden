package com.example.mygarden.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.mygarden.data.AppDatabase
import com.example.mygarden.data.local.localModels.PlantEntity
import com.example.mygarden.utilitis.PLANT_DATA_FILENAME
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlantDatabaseWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            applicationContext.assets.open(PLANT_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val plantType = object : TypeToken<List<PlantEntity>>() {}.type
                    val plantList: List<PlantEntity> = Gson().fromJson(jsonReader,plantType)
                    val database = AppDatabase.getInstance(applicationContext)
                    database.plantDao().insertAll(plantList)
                }
            }
            Result.success()
        } catch (ex : Exception) {
            Result.failure()
        }
    }

}