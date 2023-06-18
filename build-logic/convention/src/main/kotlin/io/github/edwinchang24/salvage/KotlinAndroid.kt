package io.github.edwinchang24.salvage

import com.android.build.api.dsl.CommonExtension
import io.gitlab.arturbosch.detekt.Detekt
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

const val CompileSdk = 33
const val MinSdk = 24
val JAVA_VERSION = JavaVersion.VERSION_17

internal fun Project.configureKotlinAndroid(commonExtension: CommonExtension<*, *, *, *, *>) = with(commonExtension) {
    compileSdk = CompileSdk
    defaultConfig {
        minSdk = MinSdk
    }
    compileOptions {
        sourceCompatibility = JAVA_VERSION
        targetCompatibility = JAVA_VERSION
        isCoreLibraryDesugaringEnabled = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    configureKotlin()
    dependencies {
        "coreLibraryDesugaring"(libs.findLibrary("android-desugar-jdk-libs").get())
        "detektPlugins"(libs.findLibrary("detekt-rules-compose").get())
    }
    tasks.withType<Detekt>().configureEach {
        reports.md.required.set(true)
    }
}

internal fun Project.configureKotlin() = tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JAVA_VERSION.toString()
        freeCompilerArgs += listOf(
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=kotlinx.coroutines.FlowPreview"
        )
    }
}
