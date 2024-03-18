package com.ins.boostyou.composable

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ins.boostyou.R
import com.ins.boostyou.utils.noRippleClickable
import com.ins.boostyou.viewModel.InAppPurchaseViewModel
import com.ins.boostyou.viewModel.MainActivityViewModel
import kotlinx.coroutines.launch

@Composable
fun TopCoinInfo(
    mainActivityViewModel: MainActivityViewModel,
    inAppPurchaseViewModel: InAppPurchaseViewModel
) {
    var isBuyCoinsSelected by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    if (isBuyCoinsSelected) {
        PaymentPurchaseDialog(inAppPurchaseViewModel,
            onDismissRequest = {
                isBuyCoinsSelected = false
            },
            onConfirmation = {
            }
        )
    }
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 2.dp)
            .noRippleClickable {
                Log.d("dwd","dwdwddwd")
                coroutineScope.launch {
                    inAppPurchaseViewModel.getPackageDetails()
                }
                isBuyCoinsSelected = true
            }
    ) {
        Text(
            text = if (mainActivityViewModel.userData.userName.orEmpty().isNotEmpty()) "@" + mainActivityViewModel.userData.userName.orEmpty() else "",
            modifier = Modifier.padding(end = 12.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_coin),
            contentDescription = "",
            Modifier
                .size(32.dp, 32.dp)
                .padding(top = 2.dp)
        )
        Text(
            text = mainActivityViewModel.userInfo.coinsCount.toString(),
            Modifier.padding(vertical = 4.dp).padding(start = 4.dp, end = 12.dp),
            textAlign = TextAlign.Center,
        )
    }
}