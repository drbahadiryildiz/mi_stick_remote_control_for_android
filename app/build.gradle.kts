plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.byildiz.mistickremote"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.byildiz.mistickremote"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
        }
    }

    // Java/Kotlin hedefleri
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    // Jetpack Compose
    buildFeatures { compose = true }
    composeOptions { kotlinCompilerExtensionVersion = "1.5.14" }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

/**
 * SLF4J/Log4j çakışmalarını engelle:
 * bazı bağımlılıklar eski slf4j-jdk14 / slf4j-nop veya log4j-slf4j-impl getiriyor.
 */
configurations.all {
    exclude(group = "org.slf4j", module = "slf4j-jdk14")
    exclude(group = "org.slf4j", module = "slf4j-nop")
    exclude(group = "org.apache.logging.log4j")
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2024.06.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.activity:activity-compose:1.9.2")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3:1.2.1")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // mDNS keşfi (JmDNS) — SLF4J/Log4j transitiflerini de dışla
    implementation("org.jmdns:jmdns:3.5.8") {
        exclude(group = "org.slf4j")
        exclude(group = "org.apache.logging.log4j")
    }

    // Android TV Remote protokolü (JitPack commit SHA ile sabit)
    implementation("com.github.kunal52:AndroidTvRemote:3825a2c") {
        exclude(group = "org.slf4j")
        exclude(group = "org.apache.logging.log4j")
    }

    // Tek bağlayıcı: SLF4J loglarını Android Logcat'e yönlendir
    implementation("org.slf4j:slf4j-android:1.7.36")
}
