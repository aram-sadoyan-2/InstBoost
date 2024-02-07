package com.ins.boostyou

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ins.boostyou.composable.NavigationBar
import com.ins.boostyou.viewModel.ComposeNavigationViewModel
import com.ins.boostyou.viewModel.InAppPurchaseViewModel
import com.ins.boostyou.viewModel.MainActivityViewModel
import com.ins.boostyou.viewModel.SignInUserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestActivity : ComponentActivity() {
    private val inAppPurchaseViewModel: InAppPurchaseViewModel by viewModel()
    private val signInUserViewModel: SignInUserViewModel by viewModel()
    private val composeNavigationViewModel: ComposeNavigationViewModel by viewModel()
    private val mainActivityViewModel: MainActivityViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inAppPurchaseViewModel.initialize()
        setContent {
            NavigationBar(inAppPurchaseViewModel, composeNavigationViewModel, mainActivityViewModel)
        }
    }
}

