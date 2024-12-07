import com.google.devtools.ksp.gradle.KspExtension
import io.github.santimattius.android.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidKoinConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.devtools.ksp")

            extensions.configure<KspExtension> {
                arg("KOIN_CONFIG_CHECK", "true")
            }

            dependencies {
                val bom = libs.findLibrary("koin.bom").get()
                add("implementation", platform(bom))
                add("implementation", libs.findLibrary("koin.android").get())
                add("implementation", libs.findLibrary("koin.android").get())
                add("implementation", libs.findLibrary("koin.androidx.startup").get())
                add("compileOnly", libs.findLibrary("koin.androidx.compose").get())
                add("ksp", libs.findLibrary("koin.annotations.compiler").get())
            }
        }
    }
}