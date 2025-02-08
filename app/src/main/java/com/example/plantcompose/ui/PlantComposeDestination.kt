package com.example.plantcompose.ui

/**
 * Destinations used in the [PlantComposeApp].
 */
sealed class PlantComposeDestination(val path: String) {
    data object PlantListScreen : PlantComposeDestination("list")
    data object ScanScreen : PlantComposeDestination("scan")
    data object DetailScreen : PlantComposeDestination("details/{plantId}")
}
