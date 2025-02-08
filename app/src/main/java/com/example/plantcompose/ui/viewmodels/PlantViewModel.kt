package com.example.plantcompose.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantcompose.domain.DeletePlantUseCase
import com.example.plantcompose.domain.GetPlantsUseCase
import com.example.plantcompose.domain.SavePlantsUseCase
import com.example.plantcompose.model.PlantData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

sealed class PlantUiState {
    data object Loading : PlantUiState()
    data class Success(val plants: List<PlantData>) : PlantUiState()
    data class Error(val message: String) : PlantUiState()
}

class PlantViewModel(
    private val getPlantsUseCase: GetPlantsUseCase,
    private val savePlantsUseCase: SavePlantsUseCase,
    private val deletePlantUseCase: DeletePlantUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<PlantUiState>(PlantUiState.Loading)
    val uiState: StateFlow<PlantUiState> = _uiState

    init {
        loadPlants()
    }

    private fun loadPlants() {
        viewModelScope.launch {
            getPlantsUseCase(Unit)
                .catch { error ->
                    _uiState.value = PlantUiState.Error(error.message ?: UNKNOWN_ERROR_MESSAGE)
                }
                .collect { result ->
                    result.fold(
                        onSuccess = { plants ->
                            _uiState.value = PlantUiState.Success(plants)
                        },
                        onFailure = { error ->
                            _uiState.value =
                                PlantUiState.Error(error.message ?: UNKNOWN_ERROR_MESSAGE)
                        }
                    )
                }
        }
    }

    fun savePlant(plant: PlantData) {
        viewModelScope.launch {
            savePlantsUseCase(listOf(plant))
                .catch { error ->
                    _uiState.value = PlantUiState.Error(error.message ?: UNKNOWN_ERROR_MESSAGE)
                }
                .collect { result ->
                    result.fold(
                        onSuccess = {
                            loadPlants()
                        },
                        onFailure = { error ->
                            _uiState.value =
                                PlantUiState.Error(error.message ?: UNKNOWN_ERROR_MESSAGE)
                        }
                    )
                }
        }
    }

    fun getPlant(plantId: String): PlantData? {
        return uiState.value.let { state ->
            if (state is PlantUiState.Success) {
                state.plants.find { it.id == plantId }
            } else {
                null
            }
        }
    }

    fun deletePlant(plant: PlantData) {
        viewModelScope.launch {
            deletePlantUseCase(plant)
                .catch { error ->
                    _uiState.value = PlantUiState.Error(error.message ?: UNKNOWN_ERROR_MESSAGE)
                }
                .collect { result ->
                    result.fold(
                        onSuccess = {
                            loadPlants()
                        },
                        onFailure = { error ->
                            _uiState.value =
                                PlantUiState.Error(error.message ?: UNKNOWN_ERROR_MESSAGE)
                        }
                    )
                }
        }
    }

    private companion object {
        private const val LOG_TAG = "PlantViewModel"
        private const val UNKNOWN_ERROR_MESSAGE = "Unknown error"
    }
}
