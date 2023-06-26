plugins {
    id("salvage.android.feature")
    id("salvage.android.library.compose")
}

android {
    namespace = "io.github.edwinchang24.salvage.feature.saved"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(libs.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
