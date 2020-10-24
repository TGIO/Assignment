package com.github.tgio.assignment.misc

import android.os.Looper

object LooperChecker: IThreadChecker {

    private var threadChecker: IThreadChecker
    init {
        threadChecker = object : IThreadChecker {
            override fun isMainThread() = Looper.getMainLooper().thread == Thread.currentThread()
        }
    }

    override fun isMainThread() = threadChecker.isMainThread()

    fun overrideThreadChecker(newChecker: IThreadChecker) {
        threadChecker = newChecker
    }
}