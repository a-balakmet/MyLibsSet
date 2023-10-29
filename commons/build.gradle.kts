import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    id("kotlinx-serialization")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")

    id("maven-publish")
}

android {
    namespace = "aab.lib.commons"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

val githubProperties = Properties()
githubProperties.load(FileInputStream(rootProject.file("github.properties")))

fun getVersionName(): String {
    return "1.0.0"
}

fun getLibGroupId(): String {
    return "aab.lib"
}

fun getLibArtifactId(): String {
    return "commons"
}

publishing {

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/a-balakmet/MyLibsSet")
            credentials {
                username = githubProperties["gpr.usr"] as String? ?: System.getenv("GPR_USER")
                password =
                    githubProperties["gpr.key"] as String? ?: System.getenv("GPR_API_KEY")

            }
        }
    }

    publications {
        create<MavenPublication>("ReleaseAar") {
            groupId = getLibGroupId()
            artifactId = getLibArtifactId()
            version = getVersionName()
            afterEvaluate {
                artifact(tasks.getByName("bundleReleaseAar"))
            }
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("androidx.compose.material3:material3:1.2.0-alpha10")

    val composeVersion = "1.6.0-alpha08"
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")

    val lifecycleVer = "2.6.2"
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVer")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycleVer")

    // Navigation for compose
    val navVersion = "2.7.4"
    implementation("androidx.navigation:navigation-compose:$navVersion")

    //Ktor dependencies
    val ktorVersion = "2.3.5"
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    // HTTP engine: The HTTP client used to perform network requests.
    implementation("io.ktor:ktor-client-android:$ktorVersion")
    // The serialization engine used to convert objects to and from JSON.
    implementation("io.ktor:ktor-client-serialization:$ktorVersion")
    // Logging
    implementation("io.ktor:ktor-client-logging:$ktorVersion")
    // Negotiation
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    // Json serialization
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    // Authentication
    implementation("io.ktor:ktor-client-auth:$ktorVersion")

    // Data store
    val datastoreVersion = "1.1.0-alpha05"
    implementation("androidx.datastore:datastore-preferences:$datastoreVersion")

    // Room
    val roomVersion = "2.6.0"
    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
    // To use Kotlin annotation processing tool (kapt)
    //kapt("androidx.room:room-compiler:$roomVersion")
    // To use Kotlin Symbol Processing (KSP)
    ksp("androidx.room:room-compiler:$roomVersion")
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$roomVersion")
    // optional - RxJava2 support for Room
    //implementation("androidx.room:room-rxjava2:$room_version")
    // optional - RxJava3 support for Room
    // implementation("androidx.room:room-rxjava3:$room_version")
    // optional - Guava support for Room, including Optional and ListenableFuture
    implementation("androidx.room:room-guava:$roomVersion")
    // optional - Test helpers
    testImplementation("androidx.room:room-testing:$roomVersion")
    // optional - Paging 3 Integration
    implementation("androidx.room:room-paging:$roomVersion")

    implementation("com.googlecode.libphonenumber:libphonenumber:8.13.24")

    // This lib enables to use LocalDateTime in SDK < 26
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")

    // Shared preferences
    val preferenceVer = "1.2.1"
    implementation("androidx.preference:preference-ktx:$preferenceVer")
}