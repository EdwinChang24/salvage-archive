import io.github.edwinchang24.salvage.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.project

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("salvage.android.library")
        pluginManager.apply("salvage.android.hilt")
        dependencies {
            "implementation"(project(":core:data"))
            "implementation"(project(":core:design"))
            "implementation"(project(":core:model"))
            "implementation"(project(":core:preview"))
            "implementation"(project(":core:ui"))
            "implementation"(project(":core:util"))
            "testImplementation"(kotlin("test"))
            "androidTestImplementation"(kotlin("test"))

            "implementation"(libs.findLibrary("hilt-navigation-compose").get())
            "implementation"(libs.findLibrary("lifecycle-runtime-compose").get())
            "implementation"(libs.findLibrary("lifecycle-viewmodel-compose").get())
            "implementation"(libs.findLibrary("kotlinx-coroutines-android").get())
        }
    }
}
