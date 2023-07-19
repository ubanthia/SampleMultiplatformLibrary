package com.example.multiplatformpoc

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform