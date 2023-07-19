package com.example.multiplatformpoc

class JSPlatform : Platform {
    override val name: String = "8.0"
}

actual fun getPlatform(): Platform = JSPlatform()