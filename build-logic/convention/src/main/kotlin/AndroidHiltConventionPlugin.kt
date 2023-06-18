import io.github.edwinchang24.salvage.findPluginId
import io.github.edwinchang24.salvage.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.findPluginId("hilt"))
        pluginManager.apply("org.jetbrains.kotlin.kapt")
        dependencies {
            "implementation"(libs.findLibrary("hilt.android").get())
            "kapt"(libs.findLibrary("hilt.compiler").get())
            "kaptAndroidTest"(libs.findLibrary("hilt.compiler").get())
        }
    }
}
