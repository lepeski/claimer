plugins {
    `java`
}

group = "com.example"
version = "1.0.0"

description = "Beacon-based land claim plugin"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
    withSourcesJar()
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

val paperApiVersion = providers.gradleProperty("paperApiVersion")
    .orElse("1.21.8-R0.1-SNAPSHOT")

dependencies {
    compileOnly("io.papermc.paper:paper-api:${paperApiVersion.get()}")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

