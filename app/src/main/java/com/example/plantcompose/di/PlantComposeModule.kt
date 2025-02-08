package com.example.plantcompose.di

import com.example.plantcompose.datasource.local.PlantComposeCache
import com.example.plantcompose.datasource.local.PlantComposeCacheImpl
import com.example.plantcompose.datasource.local.PlantComposePreferenceManager
import com.example.plantcompose.domain.DeletePlantUseCase
import com.example.plantcompose.domain.GetPlantsUseCase
import com.example.plantcompose.domain.SavePlantsUseCase
import com.example.plantcompose.repository.PlantComposeRepository
import com.example.plantcompose.repository.PlantComposeRepositoryImpl
import com.example.plantcompose.ui.viewmodels.PermissionViewModel
import com.example.plantcompose.ui.viewmodels.PlantViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val plantComposeDomainModule = module {
    factory {
        GetPlantsUseCase(
            plantComposeRepository = get(),
        )
    }

    factory {
        SavePlantsUseCase(
            plantComposeRepository = get(),
        )
    }

    factory {
        DeletePlantUseCase(
            plantComposeRepository = get(),
        )
    }
}

val plantComposeUiModule = module {
    // ViewModel
    viewModel {
        PlantViewModel(
            getPlantsUseCase = get(),
            savePlantsUseCase = get(),
            deletePlantUseCase = get(),
        )
    }
    viewModel {
        PermissionViewModel()
    }
}

val plantComposeFeatureModule = module {
    single<PlantComposePreferenceManager> {
        PlantComposePreferenceManager(
            context = get()
        )
    }

    single<PlantComposeCache> {
        PlantComposeCacheImpl(
            plantComposePreferenceManager = get(),
        )
    }

    single<PlantComposeRepository> {
        PlantComposeRepositoryImpl(
            localDataSource = get(),
        )
    }
}

val plantComposeModules = listOf(
    plantComposeDomainModule,
    plantComposeFeatureModule,
    plantComposeUiModule
)
