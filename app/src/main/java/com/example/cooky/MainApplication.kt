package com.example.cooky

import android.app.Application
import android.content.Context
import com.example.cooky.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        startKoin {
            androidContext(this@MainApplication)
            modules(appModules)
        }
    }

    companion object{
        lateinit var INSTANCE: MainApplication
            private set
        val applicationContext: Context?
            get() = if(::INSTANCE.isInitialized) INSTANCE.applicationContext else null
    }
}
