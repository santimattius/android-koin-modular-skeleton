plugins {
    id("io.github.santimattius.android.feature")
    id("io.github.santimattius.android.library.compose")
    id("io.github.santimattius.android.koin.library")
}

android {
    namespace = "com.santimattius.android.second.module"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.test.ext)
    androidTestImplementation(libs.test.espresso)
}