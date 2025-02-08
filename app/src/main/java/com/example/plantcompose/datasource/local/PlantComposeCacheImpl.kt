package com.example.plantcompose.datasource.local

import android.util.Log
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.plantcompose.model.PlantData
import com.example.plantcompose.utils.CustomGsonBuilder
import com.example.plantcompose.utils.fromJson
import kotlinx.coroutines.flow.firstOrNull

/**
 * Class manages local data source for [PlantData].
 *
 */
internal class PlantComposeCacheImpl internal constructor(
    private val plantComposePreferenceManager: PlantComposePreferenceManager
) : PlantComposeCache {
    /**
     * Return optional list of [PlantData].
     */
    override suspend fun getPlants(): List<PlantData>? {
        return try {
            val cachedString = plantComposePreferenceManager.readValue(Keys.plantKey)
                .firstOrNull()
            cachedString?.serializeToPlantsData() ?: emptyList()
        } catch (e: Exception) {
            // Any error in storing, return null and handle downstream.
            Log.e(LOG_TAG, "Exception getting plants from cache: ${e.message}")
            null
        }
    }

    /**
     * Put plants in cache, if the insertion operation is failed we will return null,
     * and the error will be passed to downstream.
     */
    override suspend fun putPlants(plants: List<PlantData>): Boolean {
        return try {
            val cachedString = plantComposePreferenceManager.readValue(Keys.plantKey)
                .firstOrNull()
            // append new items
            val plantsData =
                (cachedString?.serializeToPlantsData() ?: emptyList()) + plants
            // Store new list to preference
            plantsData.savePlantsDataToCache()
            true
        } catch (e: Exception) {
            // Any error in storing, return null and handle downstream.
            Log.e(LOG_TAG, "Exception inserting plants to cache: ${e.message}")
            false
        }
    }

    /**
     * Convert string from cache to plant list.
     */
    private fun String.serializeToPlantsData(): List<PlantData> {
        return if (this.isNotEmpty())
            CustomGsonBuilder.build().fromJson<List<PlantData>>(this) else emptyList()
    }

    /**
     * Save archived plants to cache.
     */
    private suspend fun List<PlantData>.savePlantsDataToCache() {
        plantComposePreferenceManager.storeValue(
            Keys.plantKey,
            CustomGsonBuilder.build().toJson(this)
        )
    }

    /**
     * Remove cache plants.
     */
    override suspend fun deletePlant(
        plantData: PlantData
    ): Boolean {
        return try {
            val cachedString = plantComposePreferenceManager.readValue(Keys.plantKey)
                .firstOrNull()
            // append new items
            val plantsData =
                (cachedString?.serializeToPlantsData() ?: emptyList()).toMutableList()
            // remove the item
            if(plantsData.isNotEmpty()) plantsData.remove(plantData)
            // Store new list to preference
            plantsData.savePlantsDataToCache()
            true
        } catch (e: Exception) {
            // Any error in storing, return null and handle downstream.
            Log.e(LOG_TAG, "Exception inserting plants to cache: ${e.message}")
            false
        }
    }

    private companion object {
        private const val LOG_TAG = "PlantComposeCacheImpl"

        private object Keys {
            val plantKey = stringPreferencesKey("plant_data")
        }
    }
}
