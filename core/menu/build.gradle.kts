plugins {
    id("salvage.android.library")
    id("salvage.android.library.compose")
}

android {
    namespace = "io.github.edwinchang24.salvage.core.menu"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    api(libs.compose.material.iconsExtended)
    api(libs.compose.material3)
    implementation(project(":core:model"))
    implementation(project(":core:util"))
    implementation(libs.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
