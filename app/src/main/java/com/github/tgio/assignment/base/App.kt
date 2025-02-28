package com.github.tgio.assignment.base

import android.app.Application
import com.github.tgio.assignment.di.NetworkModule
import com.github.tgio.assignment.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger(Level.ERROR)
            modules(
                listOf(
                    NetworkModule,
                    ViewModelModule,
                )
            )
        }
    }
}