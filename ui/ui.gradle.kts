plugins {
    id(Plugins.androidLibrary)
    kotlin(Plugins.android)
    kotlin(Plugins.kapt)
    id(Plugins.daggerHilt)
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 28
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        dataBinding = true
        resValues = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Libraries.AndroidX.Compose.compilerVersion
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
    }
}

dependencies {
    implementation(project(Projects.presentation))
    api(Libraries.AndroidX.appCompat)
    implementation(Libraries.composeThemeAdapter)
    implementation(Libraries.material)
    implementation(Libraries.AndroidX.Core.coreKtx)
    implementation(Libraries.AndroidX.Compose.Ui.ui)
    implementation(Libraries.AndroidX.Compose.Ui.uiToolingPreview)
    implementation(Libraries.AndroidX.Compose.Ui.uiTestJunit4)
    implementation(Libraries.AndroidX.Compose.Material.materialIconsCore)
    api(Libraries.AndroidX.Compose.Material.material)
    api(Libraries.Dagger.dagger)
    implementation(Libraries.Dagger.hiltCore)
    implementation(Libraries.Dagger.hiltAndroid)
    kapt(Libraries.Dagger.hiltAndroidCompiler)
    kapt(Libraries.AndroidX.Hilt.hiltCompiler)
    kapt(Libraries.Dagger.daggerCompiler)
    kapt(Libraries.AndroidX.Lifecycle.lifecycleCompiler)
    implementation(Libraries.AndroidX.Hilt.hiltNavigationCompose)
    implementation(Libraries.AndroidX.Activity.activityCompose)
    implementation("com.google.accompanist:accompanist-pager:0.25.0") // Pager
    implementation("com.google.accompanist:accompanist-pager-indicators:0.25.0")
    implementation ("com.github.jaikeerthick:Composable-Graphs:v1.0")

    testImplementation(Libraries.JUnit.jUnit)
    testImplementation(Libraries.AndroidX.Test.extJunit)
    testImplementation(Libraries.AndroidX.espresso)
    testImplementation(Libraries.AndroidX.Test.extTruth)
    androidTestImplementation(Libraries.AndroidX.Compose.Ui.uiTestJunit4)
    debugImplementation(Libraries.AndroidX.Compose.Ui.uiTooling)
}

kapt {
    correctErrorTypes = true
}