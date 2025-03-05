plugins {
    id("java-library")
    id("maven-publish")
    alias(libs.plugins.reckon)
    alias(libs.plugins.ben.manes.versions)
    alias(libs.plugins.sweng.publication)
    alias(libs.plugins.version.catalog.update)
}

group = "edu.ksu.canvas"
description = "Canvas API Library"

reckon {
    scopeFromProp()
    snapshotFromProp()
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
        vendor = JvmVendorSpec.ADOPTIUM

        withJavadocJar()
        withSourcesJar()
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    testLogging {
        events("started", "passed", "failed", "skipped")
        testLogging.showStandardStreams = true
        showStackTraces = true
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "${project.group}"
            artifactId = project.name
            version = "${project.version}"

            from(components["java"])
        }
    }
}

dependencies {
    implementation(libs.validation.api)
    implementation(libs.gson)
    implementation(libs.commons.lang3)
    implementation(libs.guava)
    implementation(libs.slf4j.api)
    implementation(libs.slf4j.simple)
    implementation(libs.httpclient5)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.httpclient5)
    testImplementation(libs.httpclient5.testing)
    testImplementation(libs.spring.beans)
    testImplementation(libs.spring.context)
    testImplementation(libs.spring.test)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.junit.jupiter)
    testImplementation(libs.hamcrest)
}


