package com.example.plantcompose

import com.example.plantcompose.domain.DeletePlantUseCase
import com.example.plantcompose.model.PlantData
import com.example.plantcompose.repository.PlantComposeRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import java.time.Instant
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DeletePlantsDataUseCaseTest {

    private lateinit var plantComposeRepository: PlantComposeRepository
    private lateinit var useCase: DeletePlantUseCase

    companion object {
        private val MOCK_PLANT = PlantData(
            id = "1",
            name = "Plant 1",
            notes = "Description 1",
            imagePath = "Image 1",
            createdAt = Instant.EPOCH
        )
    }

    @Before
    fun setup() {
        plantComposeRepository = mockk()
        useCase = DeletePlantUseCase(plantComposeRepository = plantComposeRepository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testDeletePlatDataSuccess() = runTest {
        // Given
        every {
            plantComposeRepository.deletePlant(
                plantData = MOCK_PLANT,
            )
        } returns flowOf(
            Result.success(
                Unit
            )
        )
        // When
        val result = useCase.invoke(
            MOCK_PLANT
        )
        val data = result.last().isSuccess

        // Then
        Assert.assertEquals(
            true,
            data
        )
    }

    @Test
    fun testDeletePlatDataFailed() = runTest {
        // Given
        every {
            plantComposeRepository.deletePlant(
                plantData = MOCK_PLANT,
            )
        } returns flowOf(
            Result.failure(
                Throwable("Error")
            )
        )
        // When
        val result = useCase.invoke(
            MOCK_PLANT
        )
        val data = result.last().isFailure

        // Then
        Assert.assertEquals(
            true,
            data
        )
    }
}
