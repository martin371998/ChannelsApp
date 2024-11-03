package com.example.myapp

import android.app.Application
import com.example.myapp.di.appModules
import com.example.myapp.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(appModules, networkModule)
        }
    }
}