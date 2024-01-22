package com.ins.boostyou

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.vector.ImageVector
import com.ins.boostyou.composable.FistPage
import com.ins.boostyou.composable.NavigationBar

class TestActivity: ComponentActivity() {
    //private lateinit var bindingView: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // bindingView = ActivityLoginBinding.inflate(layoutInflater)
        setContent {
           // FistPage()
            NavigationBar()
        }
    }
}

