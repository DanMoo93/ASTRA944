plugins {
    kotlin("jvm") version "2.0.21"
    application
}

group = "com.finalastra"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.netty:netty-all:4.1.94.Final")
    implementation("com.moandjiezana.toml:toml4j:0.7.2")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("ch.qos.logback:logback-classic:1.5.6")
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("com.finalastra.Server")
}

sourceSets {
    create("tools") {
        kotlin.srcDir("src/tools/kotlin")
        compileClasspath += sourceSets["main"].output + configurations["implementation"]
        runtimeClasspath += output + compileClasspath
    }
}

val toolsImplementation by configurations.creating {
    extendsFrom(configurations.implementation.get())
}

tasks.register<JavaExec>("runTool") {
    group = "application"
    description = "Runs FinalAstra tools"
    classpath = sourceSets["tools"].runtimeClasspath
    mainClass.set("com.finalastra.tools.ToolRunnerKt")
}
