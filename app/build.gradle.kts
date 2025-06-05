plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.vent"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.vent"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            // Add Firebase configuration for debug builds
            buildConfigField("String", "FIREBASE_API_KEY", "\"${project.findProperty("FIREBASE_API_KEY")}\"")
            buildConfigField("String", "FIREBASE_PROJECT_ID", "\"${project.findProperty("FIREBASE_PROJECT_ID")}\"")
            buildConfigField("String", "FIREBASE_PROJECT_NUMBER", "\"${project.findProperty("FIREBASE_PROJECT_NUMBER")}\"")
            buildConfigField("String", "FIREBASE_APP_ID", "\"${project.findProperty("FIREBASE_APP_ID")}\"")
            buildConfigField("String", "FIREBASE_STORAGE_BUCKET", "\"${project.findProperty("FIREBASE_STORAGE_BUCKET")}\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            // Add Firebase configuration for release builds
            buildConfigField("String", "FIREBASE_API_KEY", "\"${project.findProperty("FIREBASE_API_KEY")}\"")
            buildConfigField("String", "FIREBASE_PROJECT_ID", "\"${project.findProperty("FIREBASE_PROJECT_ID")}\"")
            buildConfigField("String", "FIREBASE_PROJECT_NUMBER", "\"${project.findProperty("FIREBASE_PROJECT_NUMBER")}\"")
            buildConfigField("String", "FIREBASE_APP_ID", "\"${project.findProperty("FIREBASE_APP_ID")}\"")
            buildConfigField("String", "FIREBASE_STORAGE_BUCKET", "\"${project.findProperty("FIREBASE_STORAGE_BUCKET")}\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.common)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ("com.google.firebase:firebase-database:20.0.3");

}