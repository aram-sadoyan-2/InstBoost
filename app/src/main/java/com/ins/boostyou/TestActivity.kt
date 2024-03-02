package com.ins.boostyou

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import com.ins.boostyou.composable.NavigationBar
import com.ins.boostyou.viewModel.ComposeNavigationViewModel
import com.ins.boostyou.viewModel.InAppPurchaseViewModel
import com.ins.boostyou.viewModel.MainActivityViewModel
import com.ins.boostyou.viewModel.SignInUserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestActivity : ComponentActivity() {
    private val inAppPurchaseViewModel: InAppPurchaseViewModel by viewModel()
    private val composeNavigationViewModel: ComposeNavigationViewModel by viewModel()
    private val mainActivityViewModel: MainActivityViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(
            this ,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                }

            })
        inAppPurchaseViewModel.initialize()
        setContent {
            NavigationBar(
                inAppPurchaseViewModel,
                composeNavigationViewModel,
                mainActivityViewModel,

                )
        }
    }


}

