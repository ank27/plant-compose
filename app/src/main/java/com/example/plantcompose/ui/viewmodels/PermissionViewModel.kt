package com.example.plantcompose.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

sealed class PermissionState {
    data object NotRequested : PermissionState()
    data object Granted : PermissionState()
    data object Denied : PermissionState()
    data object ShowRationale : PermissionState()
}

class PermissionViewModel : ViewModel() {
    private val _permissionState = MutableStateFlow<PermissionState>(PermissionState.NotRequested)
    val permissionState: StateFlow<PermissionState> = _permissionState

    private val _showSettingsDialog = MutableStateFlow(false)
    val showSettingsDialog: StateFlow<Boolean> = _showSettingsDialog

    fun updateCameraPermissionStatus(isGranted: Boolean) {
        _permissionState.value = if (isGranted) {
            PermissionState.Granted
        } else {
            PermissionState.Denied
        }
    }

    fun showCameraPermissionRationale() {
        _permissionState.value = PermissionState.ShowRationale
    }

    fun resetPermissionState() {
        _permissionState.value = PermissionState.NotRequested
    }

    fun showSettingsDialog() {
        _showSettingsDialog.value = true
    }

    fun hideSettingsDialog() {
        _showSettingsDialog.value = false
    }
}
