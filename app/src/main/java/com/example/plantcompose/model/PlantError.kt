package com.example.plantcompose.model

/**
 * Represents an error that can occur when fetching plant data.
 */
sealed class PlantError(
    open val message: String? = null,
) {

    /**
     * No active network connection.
     */
    data object NoNetworkError : PlantError()

    /**
     * Unknown Error
     */
    data class UnknownError(
        override val message: String? = null,
    ) : PlantError()
}
