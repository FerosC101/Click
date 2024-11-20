plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.0.14"
}

group = "clicker"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // JavaFX
    implementation("org.openjfx:javafx-controls:23")
    implementation("org.openjfx:javafx-fxml:23")

    // MySQL Database
    implementation("mysql:mysql-connector-java:8.0.33")

    // JFreeChart for charting
    implementation("org.jfree:jfreechart:1.5.3")

    // Apache Commons utilities
    implementation("org.apache.commons:commons-lang3:3.12.0")

    // JUnit for testing
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
}

javafx {
    version = "23"
    modules("javafx.controls", "javafx.fxml")
}

tasks {
    test {
        useJUnitPlatform()
    }
}


tasks {
    test {
        useJUnitPlatform()
    }

    named<JavaExec>("run") {
        jvmArgs = listOf(
            "--module-path", configurations.runtimeClasspath.get().asPath,
            "--add-modules", "javafx.controls,javafx.fxml"
        )
    }
}

application {
    mainClass.set("clicker.Main")
}


