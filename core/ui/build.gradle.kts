plugins {
    id("io.github.santimattius.android.library")
    id("io.github.santimattius.android.library.compose")
}

android {
    namespace = "com.santimattius.android.ui"
}

dependencies {

    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)
    debugApi(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.core.ktx)

}