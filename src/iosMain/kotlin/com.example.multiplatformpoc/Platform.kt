package com.example.multiplatformpoc

class IOSPlatform: Platform {
    override val name: String = "6.0"
}

actual fun getPlatform(): Platform = IOSPlatform()