package com.example.plantcompose.ui.screen

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.design_system.composable.DesignTheme
import com.example.plantcompose.R
import com.example.plantcompose.model.PlantData
import com.example.plantcompose.ui.viewmodels.PermissionState
import com.example.plantcompose.ui.viewmodels.PermissionViewModel
import com.example.plantcompose.ui.viewmodels.PlantViewModel
import com.example.plantcompose.utils.createImageUri
import com.example.plantcompose.utils.openAppSettings
import java.time.Instant
import java.util.UUID

@Composable
fun ScanScreen(
    plantViewModel: PlantViewModel,
    permissionViewModel: PermissionViewModel,
    onRequestCameraPermission: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var photoUri by remember { mutableStateOf<Uri?>(null) }
    var capturedImageBitmap by remember { mutableStateOf<Bitmap?>(null) }

    val permissionState by permissionViewModel.permissionState.collectAsState()
    val showSettingsDialog by permissionViewModel.showSettingsDialog.collectAsState()

    LaunchedEffect(Unit) {
        onRequestCameraPermission()
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            photoUri?.let { uri ->
                try {
                    context.contentResolver.openInputStream(uri)?.use { inputStream ->
                        capturedImageBitmap = BitmapFactory.decodeStream(inputStream)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } else {
            photoUri = null
            capturedImageBitmap = null
        }
    }

    if (showSettingsDialog) {
        PermissionSettingsDialog(
            onDismiss = { permissionViewModel.hideSettingsDialog() },
            onOpenSettings = {
                permissionViewModel.hideSettingsDialog()
                context.openAppSettings()
            }
        )
    }

    // Function to handle photo capture click
    val handlePhotoCaptureClick = {
        when (permissionState) {
            PermissionState.Denied -> {
                Log.e(LOG_TAG, "Camera permission denied")
                permissionViewModel.showSettingsDialog()
            }

            PermissionState.NotRequested -> {
                Log.i(LOG_TAG, "Camera permission Not requested")
                onRequestCameraPermission()
            }

            PermissionState.Granted -> {
                Log.d(LOG_TAG, "Camera permission Granted")
                context.createImageUri()?.let { uri ->
                    photoUri = uri
                    cameraLauncher.launch(uri)
                }
            }

            PermissionState.ShowRationale -> {
                Log.w(LOG_TAG, "Camera permission ShowRationale")
                onRequestCameraPermission()
            }
        }
    }

    Scaffold(
        topBar = {
            TopBarView(
                onNavigateBack = onNavigateBack
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            // Photo capture/preview area
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.size_300))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                if (capturedImageBitmap != null) {
                    PhotoPreview(
                        capturedImageBitmap = capturedImageBitmap!!,
                        onReTakeClick = {
                            context.createImageUri()?.let { uri ->
                                photoUri = uri
                                capturedImageBitmap = null
                                cameraLauncher.launch(uri)
                            }
                        }
                    )
                } else {
                    CapturePhoto(
                        handlePhotoCaptureClick = handlePhotoCaptureClick,
                    )
                }
            }
            ShowInputFieldView(
                plantViewModel = plantViewModel,
                name = name,
                notes = notes,
                photoUri = photoUri,
                onNameValueChange = { name = it },
                onNotesValueChange = { notes = it },
                onNavigateBack = onNavigateBack
            )
        }
    }
}

@Composable
fun CapturePhoto(
    handlePhotoCaptureClick: () -> Unit?,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .clickable { handlePhotoCaptureClick() }
            .padding(DesignTheme.spaces.spaceM)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_camera),
            contentDescription = null,
            modifier = Modifier.size(DesignTheme.spaces.space3XL),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(DesignTheme.spaces.spaceXS))
        Text(
            text = stringResource(id = R.string.take_photo),
            style = DesignTheme.typography.bodyRegular,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarView(
    onNavigateBack: () -> Unit
) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.add_new_plant_title)) },
        navigationIcon = {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.back)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    )
}

@Composable
fun ShowInputFieldView(
    plantViewModel: PlantViewModel,
    name: String,
    notes: String,
    photoUri: Uri?,
    onNameValueChange: (String) -> Unit,
    onNotesValueChange: (String) -> Unit,
    onNavigateBack: () -> Unit
) {
    // Input fields
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(DesignTheme.spaces.spaceM),
        elevation = CardDefaults.cardElevation(defaultElevation = DesignTheme.spaces.space3XS),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(DesignTheme.spaces.spaceM),
            verticalArrangement = Arrangement.spacedBy(DesignTheme.spaces.spaceM)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = onNameValueChange,
                label = { Text(stringResource(id = R.string.plant_name_hint)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_plant_default),
                        contentDescription = null
                    )
                }
            )

            OutlinedTextField(
                value = notes,
                onValueChange = onNotesValueChange,
                label = { Text(stringResource(id = R.string.plant_notes_hint)) },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3,
                maxLines = 5,
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_notes),
                        contentDescription = null
                    )
                }
            )

            Button(
                onClick = {
                    if (name.isNotBlank() && photoUri != null) {
                        val plant = PlantData(
                            id = UUID.randomUUID().toString(),
                            name = name.trim(),
                            imagePath = photoUri.toString(),
                            notes = notes.trim(),
                            createdAt = Instant.now()
                        )
                        plantViewModel.savePlant(plant)
                        onNavigateBack()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = name.isNotBlank() && photoUri != null
            ) {
                Text(stringResource(id = R.string.save_plant))
            }
        }
    }
}

@Composable
fun PhotoPreview(
    capturedImageBitmap: Bitmap,
    onReTakeClick: () -> Unit
) {
    Box {
        Image(
            bitmap = capturedImageBitmap.asImageBitmap(),
            contentDescription = stringResource(id = R.string.take_photo),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Overlay gradient for button visibility
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.4f)
                        ),
                        startY = 200f
                    )
                )
        )

        // Retake button
        Button(
            onClick = {
                onReTakeClick()
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(DesignTheme.spaces.spaceM),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f),
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_camera),
                contentDescription = null,
                modifier = Modifier.size(DesignTheme.spaces.spaceL)
            )
            Spacer(modifier = Modifier.width(DesignTheme.spaces.spaceXS))
            Text(stringResource(id = R.string.retake_photo))
        }
    }
}

private const val DATE_FORMAT = "yyyy-MM-dd"
private const val LOG_TAG = "ScanScreen"