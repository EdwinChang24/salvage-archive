plugins {
    id("salvage.android.library")
    id("salvage.android.library.compose")
}

android {
    namespace = "io.github.edwinchang24.salvage.core.preview"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    api(libs.compose.ui.tooling.preview)
    implementation(project(":core:model"))
    implementation(libs.core.ktx)
    implementation(libs.kotlinx.datetime)
    testImplementation(libs.junit)
    androidTestImplementation(libs.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
