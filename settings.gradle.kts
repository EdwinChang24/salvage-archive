@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-logic")
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

rootProject.name = "Salvage"
include(":app")

include(":core:data")
include(":core:database")
include(":core:design")
include(":core:model")
include(":core:ui")

include(":feature:saved")
include(":feature:sharetarget")
