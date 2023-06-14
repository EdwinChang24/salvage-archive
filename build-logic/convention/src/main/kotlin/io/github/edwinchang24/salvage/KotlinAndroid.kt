@file:Suppress("UnstableApiUsage")

package io.github.edwinchang24.salvage

import com.android.build.api.dsl.CommonExtension
import io.gitlab.arturbosch.detekt.Detekt
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

const val CompileSdk = 33
const val MinSdk = 24
val JAVA_VERSION = JavaVersion.VERSION_11

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
    (this as ExtensionAware).extensions.configure<KotlinJvmOptions>("kotlinOptions") {
        jvmTarget = JAVA_VERSION.toString()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    dependencies {
        "coreLibraryDesugaring"(libs.findLibrary("android-desugar-jdk-libs").get())
        "detektPlugins"(libs.findLibrary("detekt-rules-compose").get())
    }
    tasks.withType<Detekt>().configureEach {
        reports.md.required.set(true)
    }
}
