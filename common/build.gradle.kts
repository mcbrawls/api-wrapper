@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
  alias(libs.plugins.kotlin)
  alias(libs.plugins.kotlinx.serialization)
}

dependencies {
  commonMainImplementation(libs.bundles.kotlinx)
}

kotlin {
  explicitApi()

  targets {
    jvm()
    js {
      browser()
      nodejs()
    }
    wasmJs()
    iosArm64()
    iosX64()
    iosSimulatorArm64()
    watchosArm32()
    watchosArm64()
    watchosX64()
    watchosSimulatorArm64()
    tvosArm64()
    tvosX64()
    tvosSimulatorArm64()
    macosX64()
    macosArm64()
    linuxX64()
    mingwX64()
  }
}
