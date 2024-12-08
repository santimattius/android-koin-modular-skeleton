import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

val groupIdentifier: String by project
val artifactVersion: String by project
val siteUrl: String by project

group = groupIdentifier
version = artifactVersion

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    website = "https://github.com/santimattius/android-project-setup/tree/main/plugins"
    vcsUrl = "${siteUrl}.git"

    plugins {
        register("androidApplicationCompose") {
            id = "${groupIdentifier}.android.application.compose"
            displayName = "Android Application Compose"
            description = "Setup your app using compose"
            tags = listOf("android", "compose")
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "${groupIdentifier}.android.application"
            displayName = "Basic Android Application Setup"
            description = "Setup your android app with basic configurations"
            tags = listOf("android")
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "${groupIdentifier}.android.library.compose"
            displayName = "Android Library Compose"
            description = "Setup your library using compose"
            tags = listOf("android", "compose")
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "${groupIdentifier}.android.library"
            displayName = "Basic Android Library Setup"
            description = "Setup your android library with basic configurations"
            tags = listOf("android")
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "${groupIdentifier}.android.feature"
            displayName = "Android Feature Setup"
            description = "Setup your android feature with basic configurations"
            tags = listOf("android")
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidKoinApplication") {
            id = "${groupIdentifier}.android.koin.application"
            displayName = "Android Koin Setup"
            description = "Setup your android app with Koin"
            tags = listOf("android")
            implementationClass = "AndroidAppKoinConventionPlugin"
        }

        register("androidKoinLibrary") {
            id = "${groupIdentifier}.android.koin.library"
            displayName = "Android Koin Setup"
            description = "Setup your android app with Koin"
            tags = listOf("android")
            implementationClass = "AndroidLibraryKoinConventionPlugin"
        }
    }
}
