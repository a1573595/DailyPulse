package com.a1573595.dailypulse.android

import android.app.Application
import com.a1573595.dailypulse.android.di.viewModelModule
import com.a1573595.dailypulse.di.sharedKoinModuleList
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DailyPulseApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@DailyPulseApplication)
            modules(sharedKoinModuleList + viewModelModule)
        }
    }
}