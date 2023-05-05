

plugins {
    kotlin("jvm") version "1.8.20"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://droidista.github.io/Katalyst/maven")
    }
}

dependencies {
    implementation("com.katalyst:katalyst:1.0.0-SNAPSHOT")
    implementation("com.katalyst:kotlin-syntax-highlighter-plugin:1.0.0-SNAPSHOT")
    implementation("com.katalyst:responsive-image:1.0.0-SNAPSHOT")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk9:1.7.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}