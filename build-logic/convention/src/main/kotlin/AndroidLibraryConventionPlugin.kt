import com.android.build.gradle.LibraryExtension
import io.github.edwinchang24.salvage.CompileSdk
import io.github.edwinchang24.salvage.configureKotlinAndroid
import io.github.edwinchang24.salvage.findPluginId
import io.github.edwinchang24.salvage.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(libs) {
            pluginManager.apply(findPluginId("android-library"))
            pluginManager.apply(findPluginId("kotlin-android"))
            pluginManager.apply(findPluginId("detekt"))
        }
        extensions.configure<LibraryExtension> {
            configureKotlinAndroid(this)
            defaultConfig.targetSdk = CompileSdk
        }
        configurations.configureEach {
            resolutionStrategy {
                force(libs.findLibrary("junit").get())
            }
        }
        dependencies {
            "androidTestImplementation"(kotlin("test"))
            "testImplementation"(kotlin("test"))
        }
    }
}
