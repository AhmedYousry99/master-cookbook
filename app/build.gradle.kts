plugins {
    alias(libs.plugins.android.application)
    id ("androidx.navigation.safeargs")
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.senseicoder.mastercookbook"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.senseicoder.mastercookbook"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.firebase.auth)
    implementation(libs.play.services.auth)
    implementation(libs.firebase.firestore)
    testImplementation(libs.junit)
    implementation(libs.lottie)
    implementation(libs.circleimageview)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.gson)
    implementation(libs.converter.gson)
    implementation(libs.retrofit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation (libs.adapter.rxjava2)
    implementation (libs.rxjava)


    implementation (libs.glide)
    annotationProcessor (libs.compiler)
    implementation (libs.room.runtime)
    annotationProcessor (libs.room.compiler)
}