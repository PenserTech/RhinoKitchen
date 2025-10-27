
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.ksp)
}

android {
    namespace = "tech.penser.rhinokm.feature.inventory"
    compileSdk = 36

    defaultConfig {
        minSdk = 26

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
        }
    }

    packaging {
        resources {
            excludes += listOf(
                "META-INF/LICENSE.md",
                "META-INF/LICENSE-notice.md"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
            freeCompilerArgs.addAll(
                "-opt-in=kotlin.uuid.ExperimentalUuidApi"
            )
        }
    }
}

ksp {
    arg("java.awt.headless", "true")
    arg("room.schemaLocation", "$projectDir/schemas")
}

dependencies {
    implementation(project(":core:domain"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.material3.window.size)
    implementation(libs.androidx.navigation)
    implementation(libs.ktx.datetime)
    testImplementation(libs.ktx.datetime)
    implementation(libs.androidx.appcompat)
//    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)

    androidTestImplementation(project(":core:test-utils"))
    testImplementation(kotlin("test"))
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.kotlinx.coroutines.test.flow)
    testImplementation(libs.mockk)
    androidTestImplementation(libs.mockk.android)

//    koin DI
    implementation(libs.bundles.koin)
    implementation(libs.koin.androidx.compose)
    androidTestImplementation(libs.koin.test)
    androidTestImplementation(libs.koin.test.junit4)

//    Room
    ksp(libs.androidx.room.compiler)
    implementation(libs.bundles.room)
    androidTestImplementation(libs.androidx.room.testing)
    androidTestImplementation(libs.androidx.arch.core)
}
