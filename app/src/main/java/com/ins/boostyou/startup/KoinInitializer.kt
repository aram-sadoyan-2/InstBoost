package com.ins.boostyou.startup

import android.content.Context
import androidx.startup.Initializer
import com.ins.boostyou.module.coroutineModule
import com.ins.boostyou.module.instBillingModule
import com.ins.boostyou.module.instModule
import com.ins.boostyou.module.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext.startKoin

class KoinInitializer : Initializer<KoinApplication> {
    override fun create(context: Context): KoinApplication {
        return  startKoin {
            androidContext(context)
            modules(
                listOf(
                    coroutineModule,
                    networkModule(context),
                    instBillingModule,
                    instModule,
                )
            )
        }
    }
    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}