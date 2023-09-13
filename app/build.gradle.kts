plugins {
    id ("com.android.application")
    id ("kotlin-android")
    id ("kotlin-parcelize")
}

android {
    namespace = "com.example.agoratesting"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.agoratesting"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures{
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled   = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
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

    //datastore
    implementation ("androidx.datastore:datastore-preferences:1.0.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    //glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")

    implementation (fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
    val agora_sdk_version = "4.1.1"

    // chat sdk
    implementation ("io.agora.rtc:chat-sdk:1.1.0")

    // case 1: full libs
    implementation ("io.agora.rtc:full-sdk:${agora_sdk_version}")
    implementation ("io.agora.rtc:full-screen-sharing:${agora_sdk_version}")


    // case 2: partial libs
    // implementation "io.agora.rtc:full-rtc-basic:${agora_sdk_version}"
    // implementation "io.agora.rtc:ains:${agora_sdk_version}"
    // implementation "io.agora.rtc:full-content-inspect:${agora_sdk_version}"
    // implementation "io.agora.rtc:full-virtual-background:${agora_sdk_version}"
    // implementation "io.agora.rtc:full-super-resolution:${agora_sdk_version}"
    // implementation "io.agora.rtc:spatial-audio:${agora_sdk_version}"
    // implementation "io.agora.rtc:audio-beauty:${agora_sdk_version}"
    // implementation "io.agora.rtc:clear-vision:${agora_sdk_version}"
    // implementation "io.agora.rtc:pvc:${agora_sdk_version}"
    // implementation "io.agora.rtc:screen-capture:${agora_sdk_version}"
    // implementation "io.agora.rtc:aiaec:${agora_sdk_version}"
    // implementation "io.agora.rtc:drm-loader:${agora_sdk_version}"
    // implementation "io.agora.rtc:drm:${agora_sdk_version}"
    // implementation "io.agora.rtc:full-vqa:${agora_sdk_version}"

    implementation ("androidx.core:core-ktx:1.8.0")
    implementation ("androidx.appcompat:appcompat:1.4.1")
    implementation ("com.google.android.material:material:1.5.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.3")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")


}