import com.android.build.gradle.TestExtension
import io.github.edwinchang24.salvage.CompileSdk
import io.github.edwinchang24.salvage.libs
import io.github.edwinchang24.salvage.configureKotlinAndroid
import io.github.edwinchang24.salvage.findPluginId
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

@Suppress("unused")
class AndroidTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(libs) {
            pluginManager.apply(findPluginId("android-test"))
            pluginManager.apply(findPluginId("kotlin-android"))
            pluginManager.apply(findPluginId("detekt"))
        }
        extensions.configure<TestExtension> {
            configureKotlinAndroid(this)
            defaultConfig.targetSdk = CompileSdk
        }
    }
}
