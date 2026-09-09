plugins { id("com.android.application"); id("org.jetbrains.kotlin.android") }

android { namespace = "com.aryanpour.repostyar"; compileSdk = 34
    defaultConfig { applicationId = "com.aryanpour.repostyar"; minSdk = 24; targetSdk = 34; versionCode = 2; versionName = "0.2.0" }
}

dependencies {
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
}
