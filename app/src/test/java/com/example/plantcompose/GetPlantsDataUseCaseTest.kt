package com.example.plantcompose

import com.example.plantcompose.domain.GetPlantsUseCase
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

class GetPlantsDataUseCaseTest {

    private lateinit var plantComposeRepository: PlantComposeRepository
    private lateinit var useCase: GetPlantsUseCase

    companion object {
        private val MOCK_PLANTS = listOf(
            PlantData(
                id = "1",
                name = "Plant 1",
                notes = "Description 1",
                imagePath = "Image 1",
                createdAt = Instant.EPOCH
            ),
            PlantData(
                id = "2",
                name = "Plant 2",
                notes = "Description 2",
                imagePath = "Image 2",
                createdAt = Instant.EPOCH
            )
        )
    }

    @Before
    fun setup() {
        plantComposeRepository = mockk()
        useCase = GetPlantsUseCase(plantComposeRepository = plantComposeRepository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testGetPlantsSuccessUseCase() = runTest {
        // Given
        every {
            plantComposeRepository.getPlants()
        } returns flowOf(
            Result.success(
                MOCK_PLANTS
            )
        )
        // When
        val result = useCase.invoke(Unit)
        val data = result.last().getOrNull()

        // Then
        Assert.assertNotNull(data)
        Assert.assertEquals(
            MOCK_PLANTS,
            data
        )
    }

    @Test
    fun testGetPlantsFailedUseCase() = runTest {
        // Given
        every {
            plantComposeRepository.getPlants()
        } returns flowOf(
            Result.failure(
                Throwable("Error")
            )
        )
        // When
        val result = useCase.invoke(Unit)
        val data = result.last().getOrNull()

        // Then
        Assert.assertNull(data)
    }
}
