pluginManagement {
    repositories {
        // AGP burada -> ŞART
        google()
        // Kotlin plugin vb.
        mavenCentral()
        // Gradle plugin portalı
        gradlePluginPortal()
        // JitPack (AndroidTvRemote için)
        maven(url = "https://jitpack.io")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // Android & Jetpack
        google()
        // JmDNS, slf4j-android vb.
        mavenCentral()
        // JitPack (AndroidTvRemote)
        maven(url = "https://jitpack.io")
    }
}

rootProject.name = "MiStickRemote"
include(":app")
