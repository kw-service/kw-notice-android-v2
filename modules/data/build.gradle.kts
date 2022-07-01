plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "dev.yjyoon.kwnotice.data"
    compileSdk = 32

    defaultConfig {
        minSdk = 24
        targetSdk = 32
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":domain"))
}