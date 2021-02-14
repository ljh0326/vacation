import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    // domain project
    implementation(project(":vacation-domain"))

    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // jwt Token
    implementation ("io.jsonwebtoken:jjwt-api:0.11.2")
    implementation ("io.jsonwebtoken:jjwt-impl:0.11.2")
    implementation ("io.jsonwebtoken:jjwt-jackson:0.11.2")

    // codec
    implementation ("commons-codec:commons-codec:1.15")

    implementation("org.springframework.boot:spring-boot-starter-web")

    // test
    testImplementation ("com.willowtreeapps.assertk:assertk-jvm:0.23.1")

}
