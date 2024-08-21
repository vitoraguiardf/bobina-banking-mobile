import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.vitoraguiardf.bobinabanking"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.vitoraguiardf.bobinabanking"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    fun getProperties(file: String): Properties {
        if(!rootProject.file(file).exists()) {
            rootProject.file("app.example.properties")
                .copyTo(rootProject.file(file))
        }
        val properties = Properties()
        FileInputStream(rootProject.file(file)).let { stream ->
            properties.load(stream)
        }
        return properties
    }

    buildTypes {
        debug {
            getProperties("app.development.properties")
                .forEach { (key, value) ->
                    resValue("string", key.toString(), value.toString())
                }
        }
        release {
            getProperties("app.production.properties")
                .forEach { (key, value) ->
                    resValue("string", key.toString(), value.toString())
                }
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
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.exifinterface)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)


    // Retrofit2
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Android Room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    //noinspection KaptUsageInsteadOfKsp
    kapt(libs.androidx.room.compiler)
    // ksp("androidx.room:room-compiler:$roomVersion")       // To use Kotlin Symbol Processing (KSP)
    implementation(libs.androidx.room.ktx)                   // optional - Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.rxjava2)               // optional - RxJava2 support for Room
    implementation(libs.androidx.room.rxjava3)               // optional - RxJava3 support for Room
    implementation(libs.androidx.room.guava)                 // optional - Guava support for Room, including Optional and ListenableFuture
    implementation(libs.androidx.room.paging)                // optional - Paging 3 Integration
    implementation(libs.androidx.datastore.preferences.core)   // Android Datastore


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}