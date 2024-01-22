package com.ins.boostyou.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.startup.AppInitializer
import com.ins.boostyou.TestActivity
import com.ins.boostyou.controller.FileDataUtils
import com.ins.boostyou.startup.KoinInitializer


class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // val initializer = AppInitializer.getInstance(this)
        //initializer.initializeComponent(KoinInitializer::class.java)
        checkForTokenAndStartFlow()
    }

    private fun checkForTokenAndStartFlow() {
        val username = FileDataUtils.getUsNameFromLocal(this)
        if (username.isNotEmpty()) {
            startActivity(Intent(this@SplashScreenActivity, TestActivity::class.java))
        } else {
           // startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
            startActivity(Intent(this@SplashScreenActivity, TestActivity::class.java))
        }
        finish()
    }


}