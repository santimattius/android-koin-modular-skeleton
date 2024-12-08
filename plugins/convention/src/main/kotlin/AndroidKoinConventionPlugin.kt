import com.android.build.gradle.AbstractAppExtension
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
            extensions.configure<AbstractAppExtension>{
                // Set KSP sourceSet
                applicationVariants.forEach { variant ->
                    variant.sourceSets.forEach {
                        it.javaDirectories += files("build/generated/ksp/${variant.name}/kotlin")
                    }
                }
            }


            dependencies {
                val bom = libs.findLibrary("koin.bom").get()
                add("implementation", platform(bom))
                add("implementation", libs.findLibrary("koin.android").get())
                add("implementation", libs.findLibrary("koin.androidx.compose").get())
                add("implementation", libs.findLibrary("koin.androidx.startup").get())
                add("compileOnly", libs.findLibrary("koin.annotations.core").get())
                add("ksp", libs.findLibrary("koin.annotations.compiler").get())
            }
        }
    }
}