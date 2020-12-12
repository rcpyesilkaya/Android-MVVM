package com.recepyesilkaya.koin_sample

import android.app.Application
import com.recepyesilkaya.koin_sample.di.appModule
import com.recepyesilkaya.koin_sample.di.networkModule
import com.recepyesilkaya.koin_sample.di.roomModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(appModule, networkModule, roomModule))
        }

    }
}