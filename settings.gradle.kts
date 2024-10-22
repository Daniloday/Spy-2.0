pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "SpyCompose"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core:ui")
include(":core:navigation")
include(":feature:rules")
include(":feature:game_options")
include(":core:common")
include(":feature:game")
include(":feature:guide")
include(":feature:settings")
include(":core:data")
include(":core:datastore")
include(":core:database")
include(":core:model")
include(":feature:collections")
include(":feature:words")
include(":core:domain")
include(":core:device")
include(":core:advertising")
