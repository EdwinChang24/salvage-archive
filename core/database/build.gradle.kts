plugins {
    id("salvage.android.library")
    id("salvage.android.room")
}

android {
    namespace = "io.github.edwinchang24.salvage.database"
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(project(":core:model"))
    implementation(libs.kotlinx.datetime)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
