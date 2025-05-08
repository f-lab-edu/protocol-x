plugins {
    kotlin("jvm")
    application
}

application {
    mainClass.set("com.exchange.protocolx.matchingengine.MatchingEngineApplicationKt")
}

dependencies {
}

tasks.jar {
    enabled = true
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }) {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
}
