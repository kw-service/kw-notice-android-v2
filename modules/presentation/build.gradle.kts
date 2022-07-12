plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "dev.yjyoon.kwnotice.presentation"
    compileSdk = 32

    defaultConfig {
        minSdk = 24
        targetSdk = 32

        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        freeCompilerArgs += listOf(
            "-Xopt-in=kotlin.RequiresOptIn",
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
            "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
        )
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime)

    implementation(libs.bundles.compose)

    implementation(libs.google.accompanist.webview)
    implementation(libs.google.accompanist.systemuicontroller)
    implementation(libs.google.accompanist.navigation.animation)
    implementation(libs.google.accompanist.pager)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    coreLibraryDesugaring(libs.android.desugar)
}