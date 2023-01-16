import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.1"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"

    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
    id("org.jlleitschuh.gradle.ktlint-idea") version "11.0.0"

    id("org.asciidoctor.jvm.convert") version "3.3.2"
}

group = "com.bikemap"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

val asciidoctorExt: Configuration by configurations.creating // (2)
val snippetsDir by extra { "build/generated-snippets" }

repositories {
    mavenCentral()
}

dependencies {
    // spring web
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // devTool
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
        exclude(module = "mockito-core")
    }

    // jwt
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

    // kotest
    testImplementation("io.kotest:kotest-runner-junit5:5.5.4")
    testImplementation("io.kotest:kotest-assertions-core:5.5.4")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")

    // springmokk
    testImplementation("com.ninja-squad:springmockk:4.0.0")

    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.h2database:h2:2.1.214")

    // mariadb
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")

    // spring-Rest-Docs
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc:3.0.0")

    // asciidoctor
    asciidoctorExt("org.springframework.restdocs:spring-restdocs-asciidoctor:3.0.0")

    // Validation
    implementation("org.springframework.boot:spring-boot-starter-validation:3.0.1")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }
    withType<Test> {
        useJUnitPlatform()
    }
    test {
        useJUnitPlatform()
        outputs.dir(snippetsDir)
    }
    asciidoctor {
        doFirst {
            delete("src/main/resources/static/docs")
        }
        inputs.dir(snippetsDir)
        configurations("asciidoctorExt")
        dependsOn(test)
        baseDirFollowsSourceFile()
    }
    register<Copy>("copyDocs") {
        dependsOn(asciidoctor)
        file(".")
        from(asciidoctor.get().outputDir)
        into("src/main/resources/static/docs")
    }
    build {
        dependsOn("copyDocs")
    }
    bootJar {
        dependsOn(asciidoctor)
        from(asciidoctor.get().outputDir) {
            into("static/docs")
        }
    }
}
