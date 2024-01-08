package com.ins.boostyou

import android.app.Application
import com.ins.boostyou.module.coroutineModule
import com.ins.boostyou.module.instModule
import com.ins.boostyou.module.networkModule
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
                    instModule,
                )
            )
        }
    }
}