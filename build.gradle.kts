plugins {
  alias(libs.plugins.ktlint)
  alias(libs.plugins.kotlin) apply false
  alias(libs.plugins.kotlinx.serialization) apply false
}

subprojects {
  version = rootProject.findProperty("version")!!
  group = "net.mcbrawls.api.wrapper"

  repositories {
    mavenCentral()
    maven("https://maven.radsteve.net/public")
  }

  apply(plugin = "org.jlleitschuh.gradle.ktlint")

  ktlint {
    version.set("1.5.0")
  }

  afterEvaluate {
    dependencies {
      ktlintRuleset(libs.ktlint.extras)
    }
  }
}

fun delegatingTask(name: String, vararg delegateTasks: String) {
  tasks.register(name) {
    childProjects.forEach { (_, project) ->
      runCatching {
        delegateTasks.forEach { task -> dependsOn(project.tasks.getByName(task)) }
      }
    }
  }
}

delegatingTask("lint", "ktlintFormat")
