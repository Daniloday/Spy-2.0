plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.missclick.spy"
    compileSdk = libs.versions.androidSdk.compile.get().toInt()

    defaultConfig {
        applicationId = "com.missclick.spy"
        minSdk = libs.versions.androidSdk.min.get().toInt()
        targetSdk = libs.versions.androidSdk.target.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        resourceConfigurations += setOf("ru", "en", "uk")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(projects.core.navigation)
    implementation(projects.core.ui)
    implementation(projects.core.data)
    implementation(projects.core.datastore)
    implementation(projects.feature.gameOptions)
    implementation(projects.feature.game)
    implementation(projects.feature.words)
    implementation(projects.feature.collections)
    implementation(projects.core.database)
    implementation(projects.core.domain)
    implementation(projects.core.device)

    //koin
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)

}