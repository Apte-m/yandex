import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    application
    id("io.qameta.allure") version "2.11.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    testImplementation("com.squareup.retrofit2:converter-jackson:2.9.0")
    testImplementation("io.rest-assured:kotlin-extensions:5.3.0")
    testImplementation("io.qameta.allure:allure-junit5:2.21.0") {
        exclude(module = "junit-jupiter-api")
        exclude(module = "junit-jupiter-engine")
        exclude(module = "junit-platform-commons")
        exclude(module = "junit-platform-engine")
        exclude(module = "junit-platform-launcher")
        exclude(module = "junit-platform-runner")


    }
    implementation("io.qameta.allure:allure-rest-assured:2.21.0")

    tasks.test {
        useJUnitPlatform()
    }


    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    application {
        mainClass.set("MainKt")
    }
}