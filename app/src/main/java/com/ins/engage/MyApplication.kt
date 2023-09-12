package com.ins.engage

import android.app.Application
import com.ins.engage.module.coroutineModule
import com.ins.engage.module.instModule
import com.ins.engage.module.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    coroutineModule,
                    networkModule(applicationContext),
                    instModule
                )
            )
        }
    }
}