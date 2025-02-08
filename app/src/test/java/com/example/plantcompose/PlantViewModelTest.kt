package com.example.plantcompose

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.plantcompose.domain.DeletePlantUseCase
import com.example.plantcompose.domain.GetPlantsUseCase
import com.example.plantcompose.domain.SavePlantsUseCase
import com.example.plantcompose.model.PlantData
import com.example.plantcompose.ui.viewmodels.PlantUiState
import com.example.plantcompose.ui.viewmodels.PlantViewModel
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.time.Instant
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
class PlantViewModelTest {

    @get:Rule
    var instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var getPlantsUseCase: GetPlantsUseCase
    private lateinit var savePlantsUseCase: SavePlantsUseCase
    private lateinit var deletePlantUseCase: DeletePlantUseCase
    private lateinit var viewModel: PlantViewModel

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        getPlantsUseCase = mockk(relaxed = true)
        savePlantsUseCase = mockk(relaxed = true)
        deletePlantUseCase = mockk(relaxed = true)
        viewModel = PlantViewModel(getPlantsUseCase, savePlantsUseCase, deletePlantUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state is Loading`() = runTest {
        val initialState = viewModel.uiState.value
        assertTrue(initialState is PlantUiState.Loading)
    }

    @Test
    fun `savePlant success reloads plants`() = runTest {
        // Given
        val plant = TEST_PLANT
        coEvery { savePlantsUseCase(listOf(plant)) } returns flowOf(Result.success(Unit))
        coEvery { getPlantsUseCase(Unit) } returns flowOf(Result.success(listOf(plant)))

        // When
        viewModel.savePlant(plant)
        testScheduler.advanceUntilIdle()

        // Then
        val currentState = viewModel.uiState.value
        assertTrue(currentState is PlantUiState.Success)
        assertEquals(listOf(plant), (currentState as PlantUiState.Success).plants)
        coVerify(exactly = 1) { savePlantsUseCase(listOf(plant)) }
        coVerify(exactly = 2) { getPlantsUseCase(Unit) } // Initial load + reload after save
    }

    @Test
    fun `savePlant error updates state to Error`() = runTest {
        // Given
        val errorMessage = "Failed to save plant"
        coEvery { savePlantsUseCase(listOf(TEST_PLANT)) } returns flowOf(
            Result.failure(Exception(errorMessage))
        )

        // When
        viewModel.savePlant(TEST_PLANT)
        testScheduler.advanceUntilIdle()

        // Then
        val currentState = viewModel.uiState.value
        assertTrue(currentState is PlantUiState.Error)
        assertEquals(errorMessage, (currentState as PlantUiState.Error).message)
    }

    @Test
    fun `deletePlant success reloads plants`() = runTest {
        // Given
        val plant = TEST_PLANT
        coEvery { deletePlantUseCase(plant) } returns flowOf(Result.success(Unit))
        coEvery { getPlantsUseCase(Unit) } returns flowOf(Result.success(emptyList()))

        // When
        viewModel.deletePlant(plant)
        testScheduler.advanceUntilIdle()

        // Then
        val currentState = viewModel.uiState.value
        assertTrue(currentState is PlantUiState.Success)
        assertEquals(emptyList<PlantData>(), (currentState as PlantUiState.Success).plants)
        coVerify(exactly = 1) { deletePlantUseCase(plant) }
        coVerify(exactly = 2) { getPlantsUseCase(Unit) } // Initial load + reload after delete
    }

    @Test
    fun `getPlant returns correct plant when exists`() = runTest {
        // Given
        val plants = listOf(TEST_PLANT)
        coEvery { getPlantsUseCase(Unit) } returns flowOf(Result.success(plants))

        // When
        viewModel = PlantViewModel(getPlantsUseCase, savePlantsUseCase, deletePlantUseCase)
        testScheduler.advanceUntilIdle()

        // Then
        val plant = viewModel.getPlant(TEST_PLANT.id)
        assertEquals(TEST_PLANT, plant)
    }

    @Test
    fun `getPlant returns null when plant doesn't exist`() = runTest {
        // Given
        coEvery { getPlantsUseCase(Unit) } returns flowOf(Result.success(emptyList()))

        // When
        viewModel = PlantViewModel(getPlantsUseCase, savePlantsUseCase, deletePlantUseCase)
        testScheduler.advanceUntilIdle()

        // Then
        val plant = viewModel.getPlant("non-existent-id")
        assertEquals(null, plant)
    }

    private companion object {
        val TEST_PLANT = PlantData(
            id = "test-id",
            name = "Test Plant",
            imagePath = "test/path/image.jpg",
            notes = "Test notes",
            createdAt = Instant.EPOCH
        )
    }
}
