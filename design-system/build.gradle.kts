plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.design_system"
    compileSdk = AndroidVersions.compileSdkVersion

    defaultConfig {
        minSdk = AndroidVersions.minSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        compose = true
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
    implementation(Dependencies.androidxAppCompat)
    implementation(Dependencies.androidxLifecycle)
    implementation(Dependencies.androidxCoreKtx)
    implementation(Dependencies.androidxRecyclerView)
    implementation(Dependencies.googleMaterialDesign)
    // compose
    implementation(Dependencies.ComposeDependencies.composeUi)
    implementation(Dependencies.ComposeDependencies.composeUiTooling)
    implementation(Dependencies.ComposeDependencies.composeUiToolingPreview)
    implementation(Dependencies.ComposeDependencies.composeMaterial)
    implementation(Dependencies.ComposeDependencies.composeActivity)
    implementation(Dependencies.ComposeDependencies.composeViewModel)

    implementation(Dependencies.androidxLifecycleKtx)
    implementation(Dependencies.coroutines)

    androidTestImplementation(Dependencies.androidxTestRunner)
    androidTestImplementation(Dependencies.androidxJUnitTestRules)
    androidTestImplementation(Dependencies.androidxRules)
    androidTestImplementation(Dependencies.androidxTestJUnit)
    androidTestImplementation(Dependencies.androidxEspresso)
    androidTestImplementation(Dependencies.androidxUiAutomator)
    testImplementation(Dependencies.junit)
}
