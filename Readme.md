# PlantCompose 🌿

PlantCompose is a modern Android application built using Jetpack Compose that helps users manage and track their plants. Users can capture photos of their plants, add notes, and maintain a digital garden collection.

## Features 🌟

- Capture photos of plants using device camera
- Add plant details including name and notes
- View plant collection in a grid layout
- View detailed information about each plant
- Delete plants from collection
- Material 3 design implementation
- Dark/Light theme support
- Edge-to-edge UI implementation

## Tech Stack 🛠️

- **Kotlin** - Primary programming language
- **Jetpack Compose** - Modern UI toolkit for native UI
- **Material 3** - Material Design components for Compose
- **Coroutines & Flow** - For asynchronous programming
- **Koin** - Dependency injection
- **DataStore** - Data storage solution
- **Coil** - Image loading library
- **Navigation Compose** - Navigation between screens
- **Camera2 API** - Camera functionality

## Architecture 🏗️

The app follows Clean Architecture principles with MVVM pattern:
📦 app
┣ 📂 data
┃ ┣ 📂 repository
┃ ┗ 📂 datasource
┃   ┗ 📂 local
┣ 📂 domain
┃ ┣ 📂 model
┃ ┗ 📂 usecase
┣ 📂 di
┣ 📂 ui
┃ ┣ 📂 screen
┃ ┗ 📂 viewmodel
┗ 📂 utils

### Key Components:

- **Repository Pattern**: Abstracts data sources and provides a clean API for data access
- **Use Cases**: Contains business logic and bridges the domain and data layers
- **ViewModel**: Manages UI state and business logic for screens
- **DataStore**: Handles local data persistence
- **Dependency Injection**: Managed by Koin for better modularity and testing

## Project Structure 📁
src
├── main
│   ├── java/com/example/plantcompose
│   │   ├── datasource
│   │   │   └── local
│   │   │       ├── PlantComposeCache.kt
│   │   │       ├── PlantComposeCacheImpl.kt
│   │   │       └── PlantComposePreferenceManager.kt
│   │   ├── di
│   │   │   └── PlantComposeModule.kt
│   │   ├── domain
│   │   │   ├── DeletePlantUseCase.kt
│   │   │   ├── GetPlantsUseCase.kt
│   │   │   └── SavePlantsUseCase.kt
│   │   ├── model
│   │   │   └── PlantData.kt
│   │   ├── repository
│   │   │   ├── PlantComposeRepository.kt
│   │   │   └── PlantComposeRepositoryImpl.kt
│   │   ├── ui
│   │   │   ├── screen
│   │   │   │   ├── DetailScreen.kt
│   │   │   │   ├── PlantListScreen.kt
│   │   │   │   └── ScanScreen.kt
│   │   │   └── viewmodel
│   │   │       ├── PermissionViewModel.kt
│   │   │       └── PlantViewModel.kt
│   │   └── utils
│   │       ├── CustomGsonBuilder.kt
│   │       ├── ExtensionsUtil.kt
│   │       └── UseCase.kt
│   └── res/
└── test/

## Getting Started 🚀

1. Clone the repository:
```bash
git clone https://github.com/username/PlantCompose.git

2. Open the project in Android Studio
3. Build and run the app on an emulator or physical device

Permission Requirements 📱
The app requires the following permissions:

Camera permission for capturing plant photos
Storage permission for saving photos

Contributing 🤝

Fork the repository
Create your feature branch (git checkout -b feature/amazing-feature)
Commit your changes (git commit -m 'Add some amazing feature')
Push to the branch (git push origin feature/amazing-feature)
Open a Pull Request

License 📄
This project is licensed under the MIT License - see the LICENSE file for details.
Acknowledgments 🙏

Material Design Guidelines
Android Jetpack Libraries
Kotlin Coroutines and Flow
Koin Dependency Injection
