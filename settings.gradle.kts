pluginManagement {
    repositories {
        gradlePluginPortal()
        maven {
            name = "github"
            url = uri("https://maven.pkg.github.com/UCBoulder/sweng-gradle-repository-config-plugin")
            credentials(PasswordCredentials::class)
        }
    }
}

rootProject.name = "canvas-api"
