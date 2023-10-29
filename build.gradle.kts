buildscript {
    val kotlinVersion = "1.9.10"

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
    }

    repositories {
        mavenCentral()
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("org.jetbrains.kotlin.jvm") version "1.6.10" apply false
    id("com.android.library") version "8.1.1" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.10"
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
}

// './gradlew publish' run in terminal to publish as package