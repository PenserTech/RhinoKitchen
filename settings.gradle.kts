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

rootProject.name = "RhinoKM"
include(":app")
include(":feature:recipes")
include(":feature:orders")
include(":feature:inventory")
include(":core:domain")
include(":core:data")
include(":feature:settings")
include(":core:ui")
include(":core:test-utils")
