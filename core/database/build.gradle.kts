plugins {
    id("salvage.android.library")
    id("salvage.android.room")
    id("salvage.android.hilt")
}

android {
    namespace = "io.github.edwinchang24.salvage.core.database"
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(project(":core:model"))
    implementation(libs.kotlinx.datetime)
    androidTestImplementation(libs.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
