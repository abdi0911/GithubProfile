import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application") // Plugin aplikasi Android
    id("org.jetbrains.kotlin.android") // Plugin Kotlin untuk Android
    id("kotlin-kapt") // Plugin Kapt untuk anotasi Kotlin
}

// Membaca file apikey.properties
val apikeyPropertiesFile = rootProject.file("apikey.properties")
val apikeyProperties = Properties()

if (apikeyPropertiesFile.exists()) {
    apikeyProperties.load(FileInputStream(apikeyPropertiesFile))
} else {
    throw GradleException("File apikey.properties tidak ditemukan di root project.")
}

android {
    namespace = "com.example.githubprofile" // Namespace Anda
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.githubprofile"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        // Definisi token sebagai bagian dari BuildConfig
        buildConfigField("String", "ACCESS_TOKEN", "\"ghp_TmOThKtL5eVgPsRM j1gHkvZ6SRIfPG0dWZGH\"")

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


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true // Mengaktifkan View Binding
        buildConfig = true // Mengaktifkan BuildConfig
    }
}

dependencies {
    // Library utama
    implementation("com.airbnb.android:lottie:5.2.0") // Animasi Lottie
    implementation("de.hdodenhof:circleimageview:3.1.0") // Gambar bulat (CircleImageView)
    kapt("com.github.bumptech.glide:compiler:4.15.1") // Glide Kapt
    implementation("com.github.bumptech.glide:glide:4.15.1") // Glide untuk gambar
    implementation("com.squareup.retrofit2:retrofit:2.9.0") // Retrofit untuk API
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // Konverter JSON ke objek
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.9") // Logging Interceptor

    // Library AndroidX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Library untuk pengujian
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17) // Menggunakan JDK 17
    }
}
