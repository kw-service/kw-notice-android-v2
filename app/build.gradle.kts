plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "dev.yjyoon.kwnotice"
    compileSdk = 32

    defaultConfig {
        applicationId = "dev.yjyoon.kwnotice"
        minSdk = 24
        targetSdk = 32
        versionCode = 4
        versionName = "2.0"
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
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":presentation"))
}