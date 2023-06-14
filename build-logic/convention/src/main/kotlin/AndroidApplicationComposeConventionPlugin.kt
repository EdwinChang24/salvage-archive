import com.android.build.api.dsl.ApplicationExtension
import io.github.edwinchang24.salvage.libs
import io.github.edwinchang24.salvage.configureAndroidCompose
import io.github.edwinchang24.salvage.findPluginId
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

@Suppress("unused")
class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.findPluginId("android-application"))
        configureAndroidCompose(target.extensions.getByType<ApplicationExtension>())
    }
}
