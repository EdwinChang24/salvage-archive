import com.android.build.api.dsl.ApplicationExtension
import io.github.edwinchang24.salvage.CompileSdk
import io.github.edwinchang24.salvage.libs
import io.github.edwinchang24.salvage.configureKotlinAndroid
import io.github.edwinchang24.salvage.findPluginId
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(libs) {
            pluginManager.apply(findPluginId("android-application"))
            pluginManager.apply(findPluginId("kotlin-android"))
            pluginManager.apply(findPluginId("detekt"))
        }
        extensions.configure<ApplicationExtension> {
            configureKotlinAndroid(this)
            defaultConfig.targetSdk = CompileSdk
        }
    }
}
