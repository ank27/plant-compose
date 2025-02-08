package com.example.plantcompose.domain

import com.example.plantcompose.model.PlantData
import com.example.plantcompose.repository.PlantComposeRepository
import com.example.plantcompose.utils.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class DeletePlantUseCase internal constructor(
    private val plantComposeRepository: PlantComposeRepository,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : FlowUseCase<PlantData, Unit>(dispatcher) {

    override fun execute(plant: PlantData): Flow<Result<Unit>> {
        return plantComposeRepository.deletePlant(plant)
    }

    private companion object {
        private const val LOG_TAG = "DeletePlantUseCase"
    }
}
