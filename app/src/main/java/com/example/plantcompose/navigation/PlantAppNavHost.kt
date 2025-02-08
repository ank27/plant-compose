package com.example.plantcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.plantcompose.ui.PlantComposeDestination
import com.example.plantcompose.ui.screen.DetailScreen
import com.example.plantcompose.ui.screen.PlantListScreen
import com.example.plantcompose.ui.screen.ScanScreen
import com.example.plantcompose.ui.viewmodels.PermissionViewModel
import com.example.plantcompose.ui.viewmodels.PlantViewModel

@Composable
fun PlantAppNavHost(
    plantViewModel: PlantViewModel,
    permissionViewModel: PermissionViewModel,
    onRequestCameraPermission: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PlantComposeDestination.PlantListScreen.path
    ) {
        composable(PlantComposeDestination.PlantListScreen.path) {
            PlantListScreen(
                viewModel = plantViewModel,
                onAddClick = { navController.navigate(PlantComposeDestination.ScanScreen.path) },
                onPlantClick = {
                    val detailsPath =
                        PlantComposeDestination.DetailScreen.path.replaceAfterLast(
                            "/",
                            it.id
                        )
                    navController.navigate(detailsPath)
                }
            )
        }
        composable(PlantComposeDestination.ScanScreen.path) {
            ScanScreen(
                plantViewModel = plantViewModel,
                permissionViewModel = permissionViewModel,
                onRequestCameraPermission = onRequestCameraPermission,
                onNavigateBack = { navController.navigateUp() }
            )
        }
        composable(
            route = PlantComposeDestination.DetailScreen.path,
            arguments = listOf(navArgument("plantId") { type = NavType.StringType })
        ) { backStackEntry ->
            val plantId = backStackEntry.arguments?.getString("plantId")
            DetailScreen(
                plantId = plantId ?: "",
                viewModel = plantViewModel,
                onNavigateBack = { navController.navigateUp() }
            )
        }
    }
}
