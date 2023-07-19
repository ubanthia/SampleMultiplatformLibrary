package com.example.multiplatformpoc

import android.annotation.TargetApi
import android.os.Build

@TargetApi(Build.VERSION_CODES.DONUT)
class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()