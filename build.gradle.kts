plugins {
    kotlin("jvm") version "1.5.0"
    application
}

group = "interview.blackjack"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation("io.kotest:kotest-runner-junit5:5.1.0")
    testImplementation("io.kotest:kotest-assertions-core:5.1.0")
}

application {
    mainClass.set("interview.blackjack.BlackJackGameKt")
}

tasks.withType<Test> {
    useJUnitPlatform()
}