buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.android.gradle)
        classpath(libs.kotlin.plugin)
        classpath(libs.google.services)
        classpath(libs.hilt.gradle)
    }
}
