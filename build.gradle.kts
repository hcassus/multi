version = "1.0.0-SNAPSHOT"
group = "com.cassus"

plugins {
    kotlin("jvm") version "1.8.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.0")
    testImplementation("org.hamcrest:hamcrest:2.2")
}

tasks.test {
    useJUnitPlatform()
}

tasks.compileKotlin {
    kotlinOptions{
        jvmTarget="17"
    }
}

tasks.compileTestKotlin {
    kotlinOptions{
        jvmTarget="17"
    }
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "multiplicative.MinimumPersistenceKt"

    }

    val dependencies = configurations
        .runtimeClasspath
        .get()
        .map(::zipTree)
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

}


