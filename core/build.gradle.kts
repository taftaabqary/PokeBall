plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
}

android {
    namespace = "com.althaaf.pokeball.core"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://pokeapi.co/api/v2/\"")
        }
        debug {
            buildConfigField("String", "BASE_URL", "\"https://pokeapi.co/api/v2/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    api(libs.androidx.core.ktx)
    api(libs.androidx.appcompat)
    api(libs.material)
    api(libs.androidx.activity)
    api(libs.androidx.constraintlayout)
    testApi(libs.junit)
    androidTestApi(libs.androidx.junit)
    androidTestApi(libs.androidx.espresso.core)

    api(libs.androidx.lifecycle.viewmodel.ktx)
    api(libs.androidx.lifecycle.livedata.ktx)
    api(libs.androidx.lifecycle.runtime.ktx)

    api(libs.kotlinx.coroutines.core)
    api(libs.jetbrains.kotlinx.coroutines.android)

    implementation(libs.retrofit)
    implementation(libs.retrofit2.converter.gson)
    implementation(libs.logging.interceptor)

    api(libs.androidx.paging3.ktx)

    api(libs.androidx.recyclerview)

    api(libs.io.insert.koin.koin.core)
    api(libs.insert.koin.koin.android)

    api(libs.glide)
    api(libs.lottie)

    api(libs.androidx.fragment.ktx)
    api(libs.androidx.activity.ktx)

    implementation(libs.androidx.room.paging)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    ksp(libs.room.compiler)

    implementation(libs.androidx.datastore.preferences)
    api(libs.androidx.viewpager2)

    api(libs.rxjava)
    api(libs.rxbinding)
}