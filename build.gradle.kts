plugins {
    id("java")
    id("maven-publish")
    id("io.github.goooler.shadow") version "8.1.8"
}

allprojects {

    project.group = "cn.encmys.ykdz.forest"
    project.version = "0.1.0-Beta"

    apply<JavaPlugin>()
    apply(plugin = "java")
    apply(plugin = "io.github.goooler.shadow")
    apply(plugin = "org.gradle.maven-publish")

    repositories {
        mavenLocal()
        mavenCentral()
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        maven("https://mvn.lumine.io/repository/maven-public/")
        maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        maven("https://maven.aliyun.com/repository/public/")
        maven("https://papermc.io/repo/repository/maven-public/")
        maven("https://oss.sonatype.org/content/groups/public/")
        maven("https://jitpack.io/")
        maven("https://maven.aliyun.com/repository/public/")
        maven("https://papermc.io/repo/repository/maven-public/")
        maven("https://oss.sonatype.org/content/groups/public/")
        maven("https://repo.dmulloy2.net/repository/public/")
        maven("https://repo.codemc.org/repository/maven-public/")
        maven("https://maven.enginehub.org/repo/")
        maven("https://jitpack.io/")
        maven("https://repo.rapture.pw/repository/maven-releases/")
        maven("https://nexus.phoenixdevt.fr/repository/maven-public/")
        maven("https://r.irepo.space/maven/")
        maven("https://repo.auxilor.io/repository/maven-public/")
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
        maven("https://repo.xenondevs.xyz/releases/")
        maven("https://nexus.phoenixdevt.fr/repository/maven-public/")
        maven("https://repo.dmulloy2.net/repository/public/")
        maven("https://repo.xenondevs.xyz/releases")
        maven("https://repo.oraxen.com/releases")
        maven("https://r.irepo.space/maven/")
    }
}

subprojects {
    tasks.processResources {
        val props = mapOf("version" to version)
        inputs.properties(props)
        filteringCharset = "UTF-8"
        filesMatching("*plugin.yml") {
            expand(props)
        }
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(17)
    }

    tasks.shadowJar {
        destinationDirectory.set(file("$rootDir/target"))
        archiveClassifier.set("")
        archiveFileName.set("SeaMechanism-" + project.name + "-" + project.version + ".jar")
    }

    if ("api" == project.name) {
        publishing {
            publications {
                create<MavenPublication>("mavenJava") {
                    groupId = "cn.encmys"
                    artifactId = "SeaMechanism"
                    version = rootProject.version.toString()
                    artifact(tasks.shadowJar)
                }
            }
        }
    }
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.21-R0.1-SNAPSHOT")

    compileOnly("io.lumine:MythicLib-dist:1.6.2-SNAPSHOT")
    compileOnly("net.Indyuce:MMOItems-API:6.9.5-SNAPSHOT")

    compileOnly("org.jetbrains:annotations:24.1.0")
    annotationProcessor("org.jetbrains:annotations:24.1.0")

    compileOnly("dev.jorel:commandapi-annotations:9.5.1")
    implementation("dev.jorel:commandapi-bukkit-shade:9.5.1")
    annotationProcessor("dev.jorel:commandapi-annotations:9.5.1")

    implementation("net.kyori:adventure-api:4.17.0")
    implementation("net.kyori:adventure-platform-bukkit:4.3.3")
    implementation("net.kyori:adventure-text-minimessage:4.17.0")

    compileOnly("me.clip:placeholderapi:2.11.6")

    compileOnly("org.xerial:sqlite-jdbc:3.45.3.0")

    implementation("com.google.code.gson:gson:2.11.0")
}

tasks {
    shadowJar {
        archiveFileName.set(rootProject.name + "-" + version + ".jar")
        relocate("dev.jorel.commandapi", "cn.encmys.ykdz.forest.dailyshop.libraries.commandapi")
        relocate("org.bstats", "cn.encmys.ykdz.forest.dailyshop.libraries.bstats")
        relocate("net.kyori", "cn.encmys.ykdz.forest.dailyshop.libraries")
        relocate("xyz.xenondevs", "cn.encmys.ykdz.forest.dailyshop.libraries")
        relocate("org.intellij.lang.annotations", "cn.encmys.ykdz.forest.dailyshop.libraries.annotations.intellij")
        relocate("org.jetbrains.annotations", "cn.encmys.ykdz.forest.dailyshop.libraries.annotations.jetbrains")
        relocate("javax.annotation", "cn.encmys.ykdz.forest.dailyshop.libraries.annotations.javax")
        relocate("com.google", "cn.encmys.ykdz.forest.dailyshop.libraries.google")
    }
}