package com.example.plantcompose.repository

import com.example.plantcompose.datasource.local.PlantComposeCache
import com.example.plantcompose.model.PlantData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow

/**
 * Repository to handle plant data.
 * @param localDataSource Local data source for plant data.
 * @param coroutineScope Coroutine scope to launch coroutine.
 *
 */
internal class PlantComposeRepositoryImpl(
    private val localDataSource: PlantComposeCache,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : PlantComposeRepository {

    override fun getPlants() = flow {
        val plants = localDataSource.getPlants()
        if (plants != null) {
            emit(Result.success(plants))
        } else {
            emit(Result.failure(Exception(FAILED_TO_FETCH_PLANTS)))
        }
    }

    override fun savePlant(plants: List<PlantData>) = flow {
        val success = localDataSource.putPlants(plants)
        if (success) {
            emit(Result.success(Unit))
        } else {
            emit(Result.failure(Exception(FAILED_TO_SAVE_PLANTS)))
        }
    }

    override fun deletePlant(plantData: PlantData) = flow {
        val success = localDataSource.deletePlant(plantData)
        if (success) {
            emit(Result.success(Unit))
        } else {
            emit(Result.failure(Exception(FAILED_TO_CLEAR_PLANTS)))
        }
    }

    private companion object {
        private const val LOG_TAG = "PlantComposeRepositoryImpl"
        private const val FAILED_TO_FETCH_PLANTS = "Failed to fetch plants!!"
        private const val FAILED_TO_SAVE_PLANTS = "Failed to save plants!!"
        private const val FAILED_TO_CLEAR_PLANTS = "Failed to clear plants!!"
    }
}
