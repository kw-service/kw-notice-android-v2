pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "KW Notice"

includeAll(
    "app",
    "presentation",
    "data",
    "domain",
)

fun includeAll(vararg names: String) {
    names.forEach { name ->
        val projectName = ":$name"
        val projectPath = "modules/$name"
        include(projectName)
        project(projectName).projectDir = file(projectPath)
    }
}
