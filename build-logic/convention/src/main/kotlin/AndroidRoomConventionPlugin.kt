import com.google.devtools.ksp.gradle.KspExtension
import io.github.edwinchang24.salvage.findPluginId
import io.github.edwinchang24.salvage.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import java.io.File

class AndroidRoomConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(libs.findPluginId("ksp"))
            extensions.configure<KspExtension> {
                arg { listOf("room.schemaLocation=${File(projectDir, "schemas").path}") }
            }
            dependencies {
                "implementation"(libs.findLibrary("room-runtime").get())
                "implementation"(libs.findLibrary("room-ktx").get())
                "ksp"(libs.findLibrary("room-compiler").get())
            }
        }
    }
}
