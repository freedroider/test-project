package com.spacewl.test.core

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        DI.setup(this)
    }
}