package com.example.multiplatformpoc

class AndroidPlatform : Platform {
    override val name: String = "Android 5.0"
}

actual fun getPlatform(): Platform = AndroidPlatform()