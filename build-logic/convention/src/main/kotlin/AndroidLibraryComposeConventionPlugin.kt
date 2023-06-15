import com.android.build.gradle.LibraryExtension
import io.github.edwinchang24.salvage.libs
import io.github.edwinchang24.salvage.configureAndroidCompose
import io.github.edwinchang24.salvage.findPluginId
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.findPluginId("android-library"))
        configureAndroidCompose(extensions.getByType<LibraryExtension>())
    }
}
