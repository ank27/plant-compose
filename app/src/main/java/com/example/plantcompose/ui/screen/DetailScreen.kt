package com.example.plantcompose.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.design_system.composable.DesignTheme
import com.example.plantcompose.R
import com.example.plantcompose.model.PlantData
import com.example.plantcompose.ui.viewmodels.PlantUiState
import com.example.plantcompose.ui.viewmodels.PlantViewModel
import com.example.plantcompose.utils.toFormattedDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    plantId: String?,
    viewModel: PlantViewModel,
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val plant = (uiState as? PlantUiState.Success)?.plants?.find { it.id == plantId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { stringResource(id = R.string.plant_detail_title) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    navigationIconContentColor = Color.White
                ),
                modifier = Modifier.statusBarsPadding()
            )
        }
    ) { padding ->
        if (plant != null) {
            Box(modifier = Modifier.fillMaxSize()) {
                // Large Image at the top
                AsyncImage(
                    model = plant.imagePath,
                    contentDescription = plant.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Crop,
                    error = painterResource(id = R.drawable.ic_plant_default)
                )

                // Gradient overlay for better text visibility
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Black.copy(alpha = R.dimen.alpha_0_3.toFloat()),
                                    Color.Transparent,
                                    Color.Transparent
                                )
                            )
                        )
                )

                // Content
                PlantDetailCard(
                    plant = plant,
                    padding = padding
                )
            }
        }
    }
}

@Composable
fun PlantDetailCard(
    plant: PlantData,
    padding: PaddingValues = PaddingValues(DesignTheme.spaces.spaceL)
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Spacer(
            modifier = Modifier.height(
                dimensionResource(id = R.dimen.size_260)
            )
        )

        // Plant details card
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(
                topStart = DesignTheme.spaces.spaceXL,
                topEnd = DesignTheme.spaces.spaceXL
            ),
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = DesignTheme.spaces.spaceXS
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(DesignTheme.spaces.spaceL)
            ) {
                Text(
                    text = plant.name,
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(DesignTheme.spaces.spaceXS))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_calendar),
                        contentDescription = null,
                        modifier = Modifier.size(DesignTheme.spaces.spaceM),
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                    Spacer(modifier = Modifier.width(DesignTheme.spaces.space3XS))
                    Text(
                        text = "Added on ${plant.createdAt.toFormattedDateTime()}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }

                plant.notes?.let {
                    Spacer(modifier = Modifier.height(DesignTheme.spaces.spaceL))
                    Text(
                        text = "Notes",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(DesignTheme.spaces.spaceXS))
                    Text(
                        text = plant.notes,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}