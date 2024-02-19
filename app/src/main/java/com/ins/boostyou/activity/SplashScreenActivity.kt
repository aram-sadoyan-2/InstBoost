package com.ins.boostyou.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.ins.boostyou.TestActivity
import com.ins.boostyou.controller.FileDataUtils
import com.ins.boostyou.utils.logD

class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // val initializer = AppInitializer.getInstance(this)
        //initializer.initializeComponent(KoinInitializer::class.java)
        checkForTokenAndStartFlow()
    }

    private fun checkForTokenAndStartFlow() {
        val username = FileDataUtils.getUsNameFromLocal(this)
        val userNameList = FileDataUtils.getUserNameList(this)
        logD("userNameList " + userNameList)
        Log.d("dwd","usNamList " + userNameList?.toMutableList())
        if (username.isNotEmpty()) {
            startActivity(Intent(this@SplashScreenActivity, TestActivity::class.java))
        } else {
           // startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
            startActivity(Intent(this@SplashScreenActivity, TestActivity::class.java))
        }
        finish()
    }


}