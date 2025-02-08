plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.plantcompose"
    compileSdk = AndroidVersions.compileSdkVersion

    defaultConfig {
        applicationId = "com.example.plantcompose"
        minSdk = AndroidVersions.minSdkVersion
        targetSdk = AndroidVersions.targetSdkVersion
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {
    implementation(project(":design-system"))
    implementation(Dependencies.androidxAppCompat)
    implementation(Dependencies.androidxLifecycle)
    implementation(Dependencies.androidxCoreKtx)
    implementation(Dependencies.androidxRecyclerView)
    implementation(Dependencies.androidxLifecycleKtx)
    implementation(Dependencies.coroutines)
    implementation(Dependencies.googleMaterialDesign)
    // Koin
    implementation(Dependencies.koin)
    implementation(Dependencies.koinCompose)
    implementation(Dependencies.koinNavigation)
    // datastore
    implementation(Dependencies.datastoreCore)
    implementation(Dependencies.datastore)
    implementation(Dependencies.retrofitGson)

    // compose
    implementation(Dependencies.ComposeDependencies.composeUi)
    implementation(Dependencies.ComposeDependencies.composeUiTooling)
    implementation(Dependencies.ComposeDependencies.composeUiToolingPreview)
    implementation(Dependencies.ComposeDependencies.composeMaterial)
    implementation(Dependencies.ComposeDependencies.composeActivity)
    implementation(Dependencies.ComposeDependencies.composeViewModel)
    implementation(Dependencies.ComposeDependencies.composeNavigation)
    implementation(Dependencies.navigationFragment)
    implementation(Dependencies.navigationUi)

    // coil
    implementation(Dependencies.coilCompose)

    // cameraX
    implementation(Dependencies.CameraDependencies.cameraX)
    implementation(Dependencies.CameraDependencies.cameraXLifecycle)
    implementation(Dependencies.CameraDependencies.cameraXView)

    androidTestImplementation(Dependencies.androidxTestRunner)
    androidTestImplementation(Dependencies.androidxJUnitTestRules)
    androidTestImplementation(Dependencies.androidxRules)
    androidTestImplementation(Dependencies.androidxTestJUnit)
    androidTestImplementation(Dependencies.androidxEspresso)
    androidTestImplementation(Dependencies.androidxUiAutomator)
    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.koinTest)
    testImplementation("io.mockk:mockk:1.13.11")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
}
