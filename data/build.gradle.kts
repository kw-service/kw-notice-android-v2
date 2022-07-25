import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

val properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())

android {
    namespace = "dev.yjyoon.kwnotice.data"
    compileSdk = 32

    defaultConfig {
        minSdk = 24
        targetSdk = 32

        buildConfigField("String", "BASE_URL", properties["base_url"] as String)
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    annotationProcessor(libs.androidx.room.compiler)
    kapt(libs.androidx.room.compiler)

    implementation(libs.androidx.datastore.preferences)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter.gson)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    coreLibraryDesugaring(libs.android.desugar)
}