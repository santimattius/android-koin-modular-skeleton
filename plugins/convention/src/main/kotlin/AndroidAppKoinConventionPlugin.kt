import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.AbstractAppExtension
import com.google.devtools.ksp.gradle.KspExtension
import io.github.santimattius.android.configureGeneratedKoinApplication
import io.github.santimattius.android.configureKoinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType

class AndroidAppKoinConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.devtools.ksp")

            extensions.configure<KspExtension> {
                arg("KOIN_CONFIG_CHECK", "true")
            }

            val abstractAppExtension = extensions.getByType<AbstractAppExtension>()
            configureGeneratedKoinApplication(abstractAppExtension)
            val applicationExtension = extensions.getByType<ApplicationExtension>()
            configureKoinAndroid(applicationExtension)
        }
    }
}