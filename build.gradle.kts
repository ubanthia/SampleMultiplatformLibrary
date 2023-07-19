import org.jetbrains.kotlin.gradle.idea.tcs.extras.sourcesClasspathKey

plugins {
    kotlin("multiplatform") version "1.9.0"
    id("com.android.library")
    id("maven-publish")
}

group = "multiplatform.poc"
version = "2.0.0"

repositories {
    google()
    mavenCentral()
}

kotlin {
    android {
        publishAllLibraryVariants()
    }
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val iosTarget = when {
        hostOs == "Mac OS X" -> macosX64("ios")
        hostOs == "Linux" -> linuxX64("ios")
        isMingwX64 -> mingwX64("ios")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }


    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting
        val jvmTest by getting
        val iosMain by getting
        val iosTest by getting
        val androidMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
    }
}

android {
    compileSdkVersion(30)
    buildToolsVersion = "30.0.3"
    defaultConfig {
        minSdkVersion(22)
        targetSdkVersion(30)
        multiDexEnabled = true
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
    sourceSets {
        getByName("main") {
            manifest.srcFile("src\\androidMain\\AndroidManifest.xml")
            java.srcDirs("src\\androidMain\\kotlin")
            res.srcDirs("src\\androidMain\\res")
        }
    }
}