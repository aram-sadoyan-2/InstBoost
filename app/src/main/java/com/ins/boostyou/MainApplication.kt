package com.ins.boostyou

import android.app.Application
import androidx.startup.AppInitializer
import com.ins.boostyou.billing.InstBoostPaymentService
import com.ins.boostyou.startup.KoinInitializer
import org.koin.android.ext.android.inject

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val initializer = AppInitializer.getInstance(this)
        initializer.initializeComponent(KoinInitializer::class.java)
        val a: InstBoostPaymentService by inject()
        a.initialize()

    }
}