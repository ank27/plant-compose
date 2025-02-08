package com.example.plantcompose.domain

import com.example.plantcompose.model.PlantData
import com.example.plantcompose.repository.PlantComposeRepository
import com.example.plantcompose.utils.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class GetPlantsUseCase internal constructor(
    private val plantComposeRepository: PlantComposeRepository,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : FlowUseCase<Unit, List<PlantData>>(dispatcher) {

    override fun execute(parameters: Unit): Flow<Result<List<PlantData>>> {
        return plantComposeRepository.getPlants()
    }

    private companion object {
        private const val LOG_TAG = "GetPlantsUseCase"
    }
}
