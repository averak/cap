plugins {
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.allopen") version "1.8.22"

    id("org.springframework.boot") version "3.1.2"
    id("org.flywaydb.flyway") version "9.10.2"
    id("com.github.ben-manes.versions") version "0.47.0"
    id("com.diffplug.spotless") version "6.21.0"

    application
    java
    groovy
    eclipse
    idea
}

buildscript {
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:3.1.2")
        classpath("io.spring.gradle:dependency-management-plugin:1.1.0")
        classpath("org.flywaydb:flyway-gradle-plugin:9.10.2")
        classpath("org.flywaydb:flyway-mysql:9.10.2")
        classpath("com.github.ben-manes:gradle-versions-plugin:0.47.0")
    }
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    // spring boot
    implementation("org.springframework.boot:spring-boot-starter-web:3.1.2")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.1.2")
    implementation("org.springframework.boot:spring-boot-starter-security:3.1.2")
    implementation("org.springframework.boot:spring-boot-starter-webflux:3.1.2")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf:3.1.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.1.2")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:3.1.2")

    // spring security
    implementation("org.springframework.security:spring-security-core:6.1.2")
    implementation("org.springframework.security:spring-security-web:6.1.2")
    implementation("org.springframework.security:spring-security-config:6.1.2")
    testImplementation("org.springframework.security:spring-security-test:6.1.2")

    // mysql
    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("org.flywaydb:flyway-core:9.10.2")
    implementation("org.flywaydb:flyway-mysql:9.10.2")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.2")
    implementation("org.mybatis.generator:mybatis-generator-maven-plugin:1.4.2")

    // swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.4")
    implementation("org.springdoc:springdoc-openapi-starter-common:2.0.4")
    implementation("com.github.therapi:therapi-runtime-javadoc:0.15.0")
    annotationProcessor("com.github.therapi:therapi-runtime-javadoc-scribe:0.15.0")

    // logging
    implementation("ch.qos.logback:logback-classic:1.4.8")
    implementation("net.logstash.logback:logstash-logback-encoder:7.4")

    // utils
    implementation("io.jsonwebtoken:jjwt:0.9.1")
    implementation("com.google.guava:guava:31.1-jre")
    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("org.apache.commons:commons-collections4:4.4")
    implementation("commons-net:commons-net:3.9.0")
    implementation("commons-validator:commons-validator:1.7")
    implementation("net.rakugakibox.util:yaml-resource-bundle:1.2")
    implementation("org.wso2.orbit.javax.xml.bind:jaxb-api:2.3.1.wso2v2")
    implementation("de.huxhorn.sulky:de.huxhorn.sulky.ulid:8.3.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2")

    // test
    testImplementation("org.spockframework:spock-core:2.4-M1-groovy-4.0")
    testImplementation("org.spockframework:spock-spring:2.4-M1-groovy-4.0")
    testImplementation("io.github.dvgaba:easy-random-core:6.1.3")
    testImplementation("org.apache.groovy:groovy:4.0.7")
    testImplementation("org.apache.groovy:groovy-sql:4.0.7")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

flyway {
    url = "jdbc:mysql://localhost:3306/db"
    user = "db"
    password = "db"
    cleanDisabled = false
}

spotless {
    java {
        eclipse().configFile("${rootDir}/config/eclipse-formatter.xml")
        trimTrailingWhitespace()
        endWithNewline()
    }

    kotlin {
        trimTrailingWhitespace()
        endWithNewline()
    }
}

tasks {
    test {
        useJUnitPlatform()

        @Suppress("UNCHECKED_CAST")
        systemProperties(System.getProperties().toMap() as Map<String, Any>)
        systemProperty("spring.profiles.active", "test")
        systemProperty("spring.test.context.cache.maxSize", 10)
    }

    jar {
        enabled = false
    }

    javadoc {
        (options as StandardJavadocDocletOptions).addBooleanOption("Xdoclint:none", true)
    }

    val mybatisGenerator: Configuration by configurations.creating
    dependencies {
        mybatisGenerator("mysql:mysql-connector-java:8.0.31")
        mybatisGenerator("org.mybatis.generator:mybatis-generator-core:1.4.1")
    }
    task("mbgenerate") {
        doLast {
            ant.withGroovyBuilder {
                "taskdef"(
                    "name" to "mbgenerator",
                    "classname" to "org.mybatis.generator.ant.GeneratorAntTask",
                    "classpath" to mybatisGenerator.asPath + ":build/classes/java/main",
                )
            }
            ant.withGroovyBuilder {
                "mbgenerator"(
                    "overwrite" to true,
                    "configfile" to "${rootDir}/src/main/resources/db/mybatis-generator-config.xml",
                    "verbose" to true,
                )
            }
        }
    }
}
