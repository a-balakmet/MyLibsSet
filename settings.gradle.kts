pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/a-balakmet/MyLibsSet")
            credentials {
                username = "61378523"
                password = "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"
            }
        }
    }
}

rootProject.name = "AAB Libs Set"
include(":commons")
include(":datetimewheel")
include(":ScannerView")
