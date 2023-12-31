plugins {
    id("salvage.android.library")
    id("salvage.android.library.compose")
}

android {
    namespace = "io.github.edwinchang24.salvage.core.util"
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(libs.browser)
    implementation(libs.core.ktx)
    implementation(libs.compose.material3)
    implementation(libs.compose.runtime)
    implementation(libs.compose.ui.graphics)
    implementation(libs.kotlinx.datetime)
    testImplementation(libs.junit)
    androidTestImplementation(libs.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
