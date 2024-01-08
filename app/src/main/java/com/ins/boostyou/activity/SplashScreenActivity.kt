package com.ins.boostyou.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.ins.boostyou.controller.FileDataUtils


class SplashScreenActivity : ComponentActivity() { //todo implement New Splash func
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkForTokenAndStartFlow()
    }

    private fun checkForTokenAndStartFlow() {
        val username = FileDataUtils.getUsNameFromLocal(this)
        if (username.isNotEmpty()) {
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
        } else {
           // startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
        }
        finish()
    }


}