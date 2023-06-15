import io.github.edwinchang24.salvage.findPluginId
import io.github.edwinchang24.salvage.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.findPluginId("kotlin-jvm"))
    }
}
