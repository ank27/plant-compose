package com.example.plantcompose

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.design_system.composable.DesignTheme
import com.example.plantcompose.navigation.PlantAppNavHost
import com.example.plantcompose.ui.viewmodels.PermissionViewModel
import com.example.plantcompose.ui.viewmodels.PlantViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val plantViewModel: PlantViewModel by viewModel()
    private val permissionViewModel: PermissionViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val cameraPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            permissionViewModel.updateCameraPermissionStatus(isGranted)
        }
        setContent {
            DesignTheme {
                PlantAppNavHost(
                    plantViewModel = plantViewModel,
                    permissionViewModel = permissionViewModel,
                    onRequestCameraPermission = {
                        when {
                            ContextCompat.checkSelfPermission(
                                this,
                                Manifest.permission.CAMERA
                            ) == PackageManager.PERMISSION_GRANTED -> {
                                Log.e(LOG_TAG, "Granted")
                                permissionViewModel.updateCameraPermissionStatus(true)
                            }

                            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                                Log.e(LOG_TAG, "Show Rationale")
                                permissionViewModel.showCameraPermissionRationale()
                            }

                            else -> {
                                Log.e(LOG_TAG, "Requesting camera permission")
                                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                            }
                        }
                    }
                )
            }
        }
    }

    private companion object {
        const val LOG_TAG = "MainActivity"
    }
}
