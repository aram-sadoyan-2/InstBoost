package com.ins.boostyou

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ins.boostyou.composable.NavigationBar
import com.ins.boostyou.viewModel.InAppPurchaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestActivity: ComponentActivity() {
    //private lateinit var bindingView: ActivityLoginBinding
    private val inAppPurchaseViewModel: InAppPurchaseViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inAppPurchaseViewModel.initialize()
       // bindingView = ActivityLoginBinding.inflate(layoutInflater)
        setContent {
           // FistPage()
            NavigationBar(inAppPurchaseViewModel)
        }
    }
}

