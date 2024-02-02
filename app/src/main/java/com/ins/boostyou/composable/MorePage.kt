package com.ins.boostyou.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.ins.boostyou.utils.findActivity
import com.ins.boostyou.viewModel.InAppPurchaseViewModel

@Composable
fun MorePage(inAppPurchaseViewModel: InAppPurchaseViewModel) {
    val context = LocalContext.current
    val activity = context.findActivity()
    Column(
        Modifier
            .background(Color.White)
    ) {
        Button(onClick = {
            inAppPurchaseViewModel.launchInAppBillingFlow(activity,"com.boost.coin30")
        }) {

        }
    }
}