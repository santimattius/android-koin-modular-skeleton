import com.android.build.gradle.LibraryExtension
import io.github.santimattius.android.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("io.github.santimattius.android.library")
            }
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    //TODO: define custom runner when create Android Testing Library.
                    testInstrumentationRunner =
                        "androidx.test.runner.AndroidJUnitRunner.AndroidJUnitRunner"
                }
                testOptions.animationsDisabled = true
            }

            dependencies {
                add("implementation", libs.findLibrary("androidx.lifecycle.runtime.ktx").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewmodel.compose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtime.compose").get())
            }
        }
    }
}
