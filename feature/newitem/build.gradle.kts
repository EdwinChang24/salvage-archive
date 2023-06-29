plugins {
    id("salvage.android.feature")
    id("salvage.android.hilt")
    id("salvage.android.library.compose")
}

android {
    namespace = "io.github.edwinchang24.salvage.feature.newitem"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.kotlinx.datetime)
    androidTestImplementation(libs.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
