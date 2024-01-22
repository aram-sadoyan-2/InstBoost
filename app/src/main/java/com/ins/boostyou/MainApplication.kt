package com.ins.boostyou

import android.app.Application
import androidx.startup.AppInitializer
import com.ins.boostyou.startup.KoinInitializer

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val initializer = AppInitializer.getInstance(this)
        initializer.initializeComponent(KoinInitializer::class.java)
//        val a: InstBoostPaymentService by inject()
//        a.initialize()

    }
}