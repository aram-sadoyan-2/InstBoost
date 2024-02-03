package com.ins.boostyou.composable

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ins.boostyou.utils.findActivity
import com.ins.boostyou.viewModel.InAppPurchaseViewModel
import com.ins.boostyou.viewModel.MainActivityViewModel

@Composable
fun MorePage(
    inAppPurchaseViewModel: InAppPurchaseViewModel,
    mainActivityViewModel: MainActivityViewModel
) {
    val context = LocalContext.current
    val activity = context.findActivity()
    Column(
        Modifier
            .background(Color.White)
    ) {
        Button(onClick = {
            throw Exception("dewfwefewf")
           // inAppPurchaseViewModel.launchInAppBillingFlow(activity,"com.boost.coin30")
            inAppPurchaseViewModel.launchInAppBillingFlow(activity,"android.test.purchased")
        }) {

        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Divider(thickness = 4.dp, color = Color(0xFFD9DDE1))
            Text(text = "Log Out",Modifier.clickable {
                mainActivityViewModel.logOut()
            }.padding(horizontal = 24.dp, vertical = 24.dp).fillMaxWidth())
        }
        Divider(thickness = 4.dp, color = Color(0xFFD9DDE1))
        

    }
}