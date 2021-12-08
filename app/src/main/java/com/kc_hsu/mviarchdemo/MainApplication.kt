package com.kc_hsu.mviarchdemo

import android.app.Application
import timber.log.Timber

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(CustomTree())
    }

    private class CustomTree : Timber.DebugTree() {
        override fun log(priority: Int, message: String?, vararg args: Any?) {
            super.log(priority, "[KCTEST] $message", *args)
        }
    }
}