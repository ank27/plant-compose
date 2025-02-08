package com.example.plantcompose.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import com.example.design_system.composable.DesignTheme
import com.example.plantcompose.R
import com.example.plantcompose.model.PlantData
import com.example.plantcompose.ui.viewmodels.PlantUiState
import com.example.plantcompose.ui.viewmodels.PlantViewModel
import com.example.plantcompose.utils.toFormattedDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantListScreen(
    viewModel: PlantViewModel,
    onAddClick: () -> Unit,
    onPlantClick: (PlantData) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.plants_title),
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add_plant_button_text),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) { padding ->
        when (uiState) {
            is PlantUiState.Loading -> LoadingScreen()
            is PlantUiState.Error -> ErrorScreen((uiState as PlantUiState.Error).message)
            is PlantUiState.Success -> PlantGrid(
                plants = (uiState as PlantUiState.Success).plants,
                onPlantClick = onPlantClick,
                onDeleteClick = { plant -> viewModel.deletePlant(plant) },
                modifier = Modifier.padding(padding)
            )
        }
    }
}

@Composable
private fun PlantGrid(
    plants: List<PlantData>,
    onPlantClick: (PlantData) -> Unit,
    onDeleteClick: (PlantData) -> Unit,
    modifier: Modifier = Modifier
) {
    if (plants.isEmpty()) {
        EmptyState(modifier)
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(GRID_CELL_SIZE),
            contentPadding = PaddingValues(DesignTheme.spaces.spaceM),
            horizontalArrangement = Arrangement.spacedBy(DesignTheme.spaces.spaceM),
            verticalArrangement = Arrangement.spacedBy(DesignTheme.spaces.spaceM),
            modifier = modifier.fillMaxSize()
        ) {
            items(
                items = plants
            ) { plant ->
                PlantGridItem(
                    plant = plant,
                    onClick = { onPlantClick(plant) },
                    onDeleteClick = { onDeleteClick(plant) }
                )
            }
        }
    }
}

@Composable
private fun PlantGridItem(
    plant: PlantData,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.8f)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = DesignTheme.spaces.space3XS)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Plant Image
            AsyncImage(
                model = plant.imagePath,
                contentDescription = plant.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.ic_plant_default)
            )

            // Overlay gradient
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = R.dimen.alpha_0_7.toFloat())
                            ),
                            startY = 100f
                        )
                    )
            )

            // Delete button
            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(id = R.string.delete_plant_description),
                    tint = Color.White.copy(alpha = R.dimen.alpha_0_9.toFloat()),
                    modifier = Modifier.size(DesignTheme.spaces.spaceL)
                )
            }

            // Plant details
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(DesignTheme.spaces.spaceS)
            ) {
                Text(
                    text = plant.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = plant.createdAt.toFormattedDateTime(),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = R.dimen.alpha_0_8.toFloat())
                )
            }
        }
    }
}

@Composable
private fun EmptyState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_empty_plant),
            contentDescription = null,
            modifier = Modifier.size(DesignTheme.spaces.space6XL),
            tint = MaterialTheme.colorScheme.primary.copy(alpha = R.dimen.alpha_0_6.toFloat())
        )
        Spacer(modifier = Modifier.height(DesignTheme.spaces.spaceM))
        Text(
            text = stringResource(id = R.string.no_plants_heading),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = R.dimen.alpha_0_6.toFloat())
        )
        Spacer(modifier = Modifier.height(DesignTheme.spaces.spaceXS))
        Text(
            text = stringResource(id = R.string.no_plants_message),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = R.dimen.alpha_0_4.toFloat()),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(DesignTheme.spaces.space3XL),
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(DesignTheme.spaces.spaceM))
            Text(
                text = stringResource(id = R.string.loading_message),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = R.dimen.alpha_0_7.toFloat())
            )
        }
    }
}

@Composable
private fun ErrorScreen(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(DesignTheme.spaces.spaceXL)
        ) {
            Icon(
                imageVector = Icons.Default.Build,
                contentDescription = null,
                modifier = Modifier.size(DesignTheme.spaces.space3XL),
                tint = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(DesignTheme.spaces.spaceM))
            Text(
                text = stringResource(id = R.string.error_message),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(DesignTheme.spaces.spaceXS))
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = R.dimen.alpha_0_8.toFloat()),
                textAlign = TextAlign.Center
            )
        }
    }
}

private const val GRID_CELL_SIZE = 2