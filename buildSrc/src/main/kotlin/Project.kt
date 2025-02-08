object AndroidVersions {
  const val minSdkVersion = 31
  const val targetSdkVersion = 34
  const val compileSdkVersion = 34
}

object Plugins {
  const val android = "com.android.tools.build:gradle:${Versions.pluginAndroidGradle}"
  const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.pluginKotlin}"
}

object Dependencies {
  const val androidxAppCompat = "androidx.appcompat:appcompat:${Versions.androidxAppcompat}"
  const val androidxRecyclerView = "androidx.recyclerview:recyclerview:${Versions.androidxRecyclerView}"
  const val androidxCoreKtx = "androidx.core:core-ktx:${Versions.androidxCore}"
  const val androidxEspresso = "androidx.test.espresso:espresso-core:${Versions.androidxEspresso}"
  const val androidxTestJUnit = "androidx.test.ext:junit:${Versions.androidxJUnit}"
  const val androidxRules = "androidx.test:rules:${Versions.androidxTest}"
  const val androidxJUnitTestRules = "androidx.test:rules:${Versions.androidxTest}"
  const val androidxTestRunner = "androidx.test:runner:${Versions.androidxTest}"
  const val androidxTestCore = "androidx.test:core:${Versions.androidxTest}"
  const val androidxArchCoreTest = "androidx.arch.core:core-testing:${Versions.androidxArchCoreTest}"
  const val androidxUiAutomator = "androidx.test.uiautomator:uiautomator:${Versions.androidxUiAutomator}"
  const val androidxFragmentTest = "androidx.fragment:fragment-testing:${Versions.androidxFragmentTesting}"
  const val androidxLifecycle = "androidx.lifecycle:lifecycle-runtime:${Versions.androidxLifecycle}"
  const val androidxLifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidxLifecycle}"
  const val androidxLifecycleRuntimeTesting = "androidx.lifecycle:lifecycle-runtime-testing:${Versions.androidxLifecycle}"
  const val androidxActivityCompose = "androidx.activity:activity-compose:${Versions.androidxActivityCompose}"
  const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.pluginKotlin}"
  const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"
  const val googleMaterialDesign = "com.google.android.material:material:${Versions.materialDesign}"
  const val junit = "junit:junit:${Versions.junit}"
  const val mockk = "io.mockk:mockk:${Versions.mockk}"
  const val mockkAgentApi = "io.mockk:mockk-agent-api:${Versions.mockk}"
  const val mockkAgentJvm = "io.mockk:mockk-agent-jvm:${Versions.mockk}"
  const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
  const val robolectricEgl = "org.khronos:opengl-api:${Versions.robolectricEgl}"
  const val lintApi = "com.android.tools.lint:lint-api:${Versions.lint}"
  const val lintChecks = "com.android.tools.lint:lint-checks:${Versions.lint}"
  const val lint = "com.android.tools.lint:lint:${Versions.lint}"
  const val lintTests = "com.android.tools.lint:lint-tests:${Versions.lint}"
  const val testUtils = "com.android.tools:testutils:${Versions.lint}"
  const val koin = "io.insert-koin:koin-android:${Versions.koin}"
  const val koinTest = "io.insert-koin:koin-test-jvm:${Versions.koinTest}"
  const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koinCompose}"
  const val koinNavigation = "io.insert-koin:koin-androidx-navigation:${Versions.koinCompose}"

  const val datastoreCore = "androidx.datastore:datastore-preferences-core:${Versions.datastorePreferenceVersion}"
  const val datastore = "androidx.datastore:datastore-preferences:${Versions.datastorePreferenceVersion}"
  const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationFragment}"
  const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigationUi}"
  const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitGson}"

  // Coil for image loading
  const val coilCompose = "io.coil-kt:coil-compose:${Versions.coilCompose}"

  object CameraDependencies {
    // Camera X
    const val cameraX = "androidx.camera:camera-camera2:${Versions.cameraX}"
    const val cameraXLifecycle = "androidx.camera:camera-lifecycle:${Versions.cameraX}"
    const val cameraXView = "androidx.camera:camera-view:${Versions.cameraX}"
  }

  object ComposeDependencies {
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material3:material3:${Versions.composeMaterial3Version}"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivityVersion}"
    const val lifecycleRuntimeCompose = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.composeLifecycleVersion}"
    const val composeViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeViewModel}"
    const val composeNavigation = "androidx.navigation:navigation-compose:${Versions.composeNavigationVersion}"
  }
}

object Versions {
  const val pluginAndroidGradle = "8.2.2"
  const val pluginKotlin = "1.9.10"
  const val androidxCore = "1.6.0"
  const val androidxFragmentTesting = "1.5.0"
  const val androidxAppcompat = "1.4.0"
  const val androidxTest = "1.4.0"
  const val androidxArchCoreTest = "2.1.0"
  const val androidxConstraintLayout = "2.0.0"
  const val androidxEspresso = "3.4.0"
  const val androidxJUnit = "1.1.3"
  const val androidxUiAutomator = "2.2.0"
  const val androidxRecyclerView = "1.1.0"
  const val androidxInterpolator="1.0.0"
  const val androidxLifecycle = "2.6.2"
  const val androidxActivityCompose = "1.8.2"
  const val kotlinCoroutines = "1.3.9"
  const val materialDesign = "1.12.0"
  const val junit = "4.13.2"
  const val mockk = "1.12.3"
  const val robolectric = "4.8.1"
  const val robolectricEgl = "gl1.1-android-2.1_r1"
  const val lint = "30.0.4"
  const val compose = "1.5.3"
  const val composeViewModel = "2.6.2"
  const val koin = "3.4.2"
  const val koinTest = "3.4.1"
  const val koinCompose = "3.4.2"
  const val composeMaterial3Version = "1.3.1"
  const val composeActivityVersion = "1.8.2"
  const val composeLifecycleVersion = "2.6.2"
  const val composeNavigationVersion = "2.7.7"
  const val datastorePreferenceVersion = "1.0.0"
  const val navigationFragment = "2.3.5"
  const val navigationUi = "2.3.5"
  const val cameraX = "1.3.0"
  const val coilCompose = "2.4.0"
  const val retrofitGson = "2.9.0"
}
