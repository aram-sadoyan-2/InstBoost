package com.ins.boostyou.startup

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import com.ins.boostyou.controller.InstKoinHolder
import com.ins.boostyou.module.coroutineModule
import com.ins.boostyou.module.instBillingModule
import com.ins.boostyou.module.instModule
import com.ins.boostyou.module.networkModule
import org.koin.core.KoinApplication
import org.koin.core.module.Module

class KoinInitializer : Initializer<KoinApplication> {
    override fun create(context: Context): KoinApplication {
        return InstKoinHolder.startKoin(context, allowOverride = false) {
            val diModules: List<Module> =
                listOf(
                    coroutineModule,
                    networkModule(context),
                    instModule,
                    instBillingModule
                )
            modules(diModules)
        }
    }

   // override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf(InAppBillingInitializer::class.java)
    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}