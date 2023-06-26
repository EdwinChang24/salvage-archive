plugins {
    id("salvage.android.library")
    id("salvage.android.library.compose")
}

android {
    namespace = "io.github.edwinchang24.salvage.design"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    api(libs.compose.foundation)
    api(libs.compose.foundation.layout)
    api(libs.compose.material3)
    api(libs.compose.runtime)
    api(libs.compose.ui.tooling.preview)
    api(libs.compose.ui.util)
    implementation(libs.core.ktx)
    androidTestImplementation(libs.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
