package com.example.plantcompose.datasource.local

import com.example.plantcompose.model.PlantData

/**
 * Cache interface for data source.
 */
internal interface PlantComposeCache {
    /**
     * get Plants from local cache.
     */
    suspend fun getPlants(): List<PlantData>?

    /**
     * put Plants in local cache.
     */
    suspend fun putPlants(plants: List<PlantData>): Boolean

    /**
     * clear Plants from local cache.
     */
    suspend fun deletePlant(plantData: PlantData): Boolean
}
