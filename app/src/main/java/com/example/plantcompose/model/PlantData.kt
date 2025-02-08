package com.example.plantcompose.model

import java.time.Instant

/**
 * Data class that represents a plant.
 * @property id Unique identifier for the plant.
 * @property name The name of the plant.
 * @property imagePath A path to the image.
 * @property notes The notes for the plant.
 * @property createdAt The date the plant was created.
 *
 */
data class PlantData(
    /**
     * Unique identifier for the plant.
     */
    val id: String,
    /**
     * The name of the plant.
     */
    val name: String,
    /**
     * A path to the image.
     */
    val imagePath: String,
    /**
     * The notes for the plant.
     */
    val notes: String? = null,
    /**
     * The date the plant was created.
     */
    val createdAt: Instant,
)
