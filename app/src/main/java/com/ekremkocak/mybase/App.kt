package com.ekremkocak.mybase

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App :Application()  {
    override fun onCreate() {
        super.onCreate()
       // Graph.provide(this)
    }
}