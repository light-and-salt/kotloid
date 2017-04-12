package kr.or.lightsalt.kotloid

import android.os.Handler
import android.os.Looper

val mainHandler = Handler(Looper.getMainLooper())

fun runOnMainThread(block: Handler.() -> Unit) = mainHandler.run(block)
