import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.kveld9.trackgym"
    compileSdk = 37

    val appVersionName: String = (project.findProperty("versionName") as? String) ?: "1.0.0"
    val appVersionCode: Int = (project.findProperty("versionCode") as? String)?.toIntOrNull() ?: 1

    defaultConfig {
        applicationId = "com.kveld9.trackgym"
        minSdk = 24
        targetSdk = 35
        versionCode = appVersionCode
        versionName = appVersionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            val localProps = Properties()
            val localPropsFile = project.rootProject.file("local.properties")
            if (localPropsFile.exists()) {
                FileInputStream(localPropsFile).use { stream -> localProps.load(stream) }
            }

            val keystorePath = System.getenv("KEYSTORE_PATH")
                ?: (project.findProperty("KEYSTORE_PATH") as? String)
                ?: localProps.getProperty("KEYSTORE_PATH")
                ?: "../release.jks"
            val keystoreFile = file(keystorePath)
            if (keystoreFile.exists()) {
                storeFile = keystoreFile
                storePassword = System.getenv("KEYSTORE_PASSWORD")
                    ?: (project.findProperty("KEYSTORE_PASSWORD") as? String)
                    ?: localProps.getProperty("KEYSTORE_PASSWORD")
                    ?: ""
                keyAlias = System.getenv("KEY_ALIAS")
                    ?: (project.findProperty("KEY_ALIAS") as? String)
                    ?: localProps.getProperty("KEY_ALIAS")
                    ?: ""
                keyPassword = System.getenv("KEY_PASSWORD")
                    ?: (project.findProperty("KEY_PASSWORD") as? String)
                    ?: localProps.getProperty("KEY_PASSWORD")
                    ?: ""
            } else {
                val debugSigning = getByName("debug")
                storeFile = debugSigning.storeFile
                storePassword = debugSigning.storePassword
                keyAlias = debugSigning.keyAlias
                keyPassword = debugSigning.keyPassword
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_11)
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.compose.icons.core)
    implementation(libs.compose.icons.extended)
    implementation(libs.material)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.kotlinx.serialization.json)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.tooling)
}
