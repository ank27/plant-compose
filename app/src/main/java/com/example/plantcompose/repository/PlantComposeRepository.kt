package com.example.plantcompose.repository

import com.example.plantcompose.model.PlantData
import kotlinx.coroutines.flow.Flow

/**
 * Repository to handle plant data.
 */
interface PlantComposeRepository {
    /**
     * Get list of plants
     * @return List of [PlantData]
     */
    fun getPlants(): Flow<Result<List<PlantData>>>

    /**
     * Save a list of plants
     * @param plants List of plants to save.
     * @return Signals Unit on success.
     */
    fun savePlant(plants: List<PlantData>): Flow<Result<Unit>>

    /**
     * Clear a plant
     *
     * @param plantData Plant to clear
     * @return Signals Unit on success
     */
    fun deletePlant(plantData: PlantData): Flow<Result<Unit>>
}
