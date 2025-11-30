plugins {
  `java-library`
}

java {
  toolchain.languageVersion = JavaLanguageVersion.of(21)
}

repositories {
  maven("https://repo.papermc.io/repository/maven-public/")
}
