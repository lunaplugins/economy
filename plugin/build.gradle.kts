plugins {
  id("java-conventions")
  id("xyz.jpenilla.run-paper") version "3.0.2"
}

dependencies {
  compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
  api(project(":api"))
}

tasks {
  withType<Jar> {
    from(project(":api").sourceSets["main"].output)
  }

  runServer {
    minecraftVersion("1.21.10")
    jvmArgs("-Xms4G", "-Xms4G", "-Dcom.mojang.eula.agree=true")
  }

  processResources {
    val version: String = project.version.toString()
    filesMatching("paper-plugin.yml") {
      expand("version" to version)
    }
  }
}
