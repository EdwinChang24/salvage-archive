plugins {
    id("salvage.android.library")
    id("salvage.android.library.compose")
}

android {
    namespace = "io.github.edwinchang24.salvage.core.ui"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    api(libs.compose.foundation)
    api(libs.compose.foundation.layout)
    api(libs.compose.material.iconsExtended)
    api(libs.compose.material3)
    api(libs.compose.runtime)
    api(libs.compose.runtime.livedata)
    api(libs.compose.ui.tooling)
    api(libs.compose.ui.tooling.preview)
    api(libs.compose.ui.util)
    implementation(project(":core:design"))
    implementation(project(":core:menu"))
    implementation(project(":core:model"))
    implementation(project(":core:preview"))
    implementation(project(":core:util"))
    implementation(libs.core.ktx)
    implementation(libs.kotlinx.datetime)
    androidTestImplementation(libs.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
