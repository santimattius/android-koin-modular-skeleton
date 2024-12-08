rootProject.name = "android-koin-modular-skeleton"
pluginManagement {
    includeBuild("plugins")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
//exclude testClasses task from plugins
gradle.startParameter.excludedTaskNames.addAll(listOf(":plugins:convention:testClasses"))

include(":app")
include(":core:ui")
include(":features:first-module")
include(":features:second-module")
