package com.example.plantcompose

import com.example.plantcompose.datasource.local.PlantComposeCache
import com.example.plantcompose.model.PlantData
import com.example.plantcompose.repository.PlantComposeRepository
import com.example.plantcompose.repository.PlantComposeRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import java.time.Instant
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PlantComposeRepositoryTest {
    private lateinit var plantComposeRepository: PlantComposeRepository
    private lateinit var localSource: PlantComposeCache
    private lateinit var plantComposeRepositoryImpl: PlantComposeRepositoryImpl

    @Before
    fun setup() {
        plantComposeRepository = mockk()
        localSource = mockk()
        plantComposeRepositoryImpl = PlantComposeRepositoryImpl(
            localDataSource = localSource
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testGetPlantsSuccess() = runTest {
        // Given
        coEvery {
            localSource.getPlants()
        } returns MOCK_PLANTS
        // When
        val result = plantComposeRepositoryImpl.getPlants().last()
        val data = result.getOrNull()
        // Then
        Assert.assertNotNull(data)
        Assert.assertEquals(
            MOCK_PLANTS,
            data
        )
    }

    @Test
    fun testGetPlantsFailure() = runTest {
        // Given
        coEvery {
            localSource.getPlants()
        } returns null
        // When
        val result = plantComposeRepositoryImpl.getPlants().last()
        val data = result.getOrNull()
        // Then
        Assert.assertNull(data)
        Assert.assertEquals(
            result.isFailure,
            true
        )
    }

    @Test
    fun testSavePlantsSuccess() = runTest {
        // Given
        coEvery {
            localSource.putPlants(MOCK_PLANTS)
        } returns true
        // When
        val result = plantComposeRepositoryImpl.savePlant(MOCK_PLANTS).last()
        val data = result.getOrNull()
        // Then
        Assert.assertNotNull(data)
        Assert.assertEquals(
            Unit,
            data
        )
    }

    @Test
    fun testSavePlantsFailure() = runTest {
        // Given
        coEvery {
            localSource.putPlants(MOCK_PLANTS)
        } returns false
        // When
        val result = plantComposeRepositoryImpl.savePlant(MOCK_PLANTS).last()
        val data = result.getOrNull()
        // Then
        Assert.assertNull(data)
        Assert.assertEquals(
            result.isFailure,
            true
        )
    }

    @Test
    fun testDeletePlantsSuccess() = runTest {
        // Given
        coEvery {
            localSource.deletePlant(MOCK_PLANTS[0])
        } returns true
        // When
        val result = plantComposeRepositoryImpl.deletePlant(MOCK_PLANTS[0]).last()
        val data = result.getOrNull()
        // Then
        Assert.assertNotNull(data)
        Assert.assertEquals(
            Unit,
            data
        )
    }

    @Test
    fun testDeletePlantsFailure() = runTest {
        // Given
        coEvery {
            localSource.deletePlant(MOCK_PLANTS[0])
        } returns false
        // When
        val result = plantComposeRepositoryImpl.deletePlant(MOCK_PLANTS[0]).last()
        val data = result.getOrNull()
        // Then
        Assert.assertNull(data)
        Assert.assertEquals(
            result.isFailure,
            true
        )
    }

    private companion object {
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
        private const val SERVER_ERROR = "Server error"
        private const val VALIDATION_ERROR = "Validation failed"
        private const val TOO_HIGH_REQUEST = "Too high request, slow down!!"
        private const val SETTINGS_STORE_ERROR = "Unable to fetch unsafe data from storage"
        private const val DELETE_ERROR = "Error while deleting message"
        private const val UNKNOWN_ERROR = "Unknown error"
        private const val NOT_FOUND_ERROR = "x-token not found"
        private const val HTTP_CODE = 500
    }
}
