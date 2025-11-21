package me.lakhtin.themecalibration

import android.app.Application
import android.content.Context

lateinit var appContext: Context
    private set

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}
