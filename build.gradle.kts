plugins {
  id("java")
  id("xyz.jpenilla.run-paper") version "3.0.2"
}

repositories {
  maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
  compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
}

java {
  toolchain.languageVersion = JavaLanguageVersion.of(21)
}

tasks {
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
