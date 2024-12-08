import com.android.build.gradle.LibraryExtension
import com.google.devtools.ksp.gradle.KspExtension
import io.github.santimattius.android.configureGeneratedKoinLibrary
import io.github.santimattius.android.configureKoinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryKoinConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.devtools.ksp")

            extensions.configure<KspExtension> {
                arg("KOIN_CONFIG_CHECK", "true")
            }
            val libraryExtension = extensions.getByType<LibraryExtension>()
            configureGeneratedKoinLibrary(libraryExtension)
            configureKoinAndroid(libraryExtension)
        }
    }
}