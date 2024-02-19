@file:Suppress("NAME_SHADOWING")

package com.ins.boostyou.composable

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ins.boostyou.R
import com.ins.boostyou.controller.FileDataUtils
import com.ins.boostyou.utils.noRippleClickable
import com.ins.boostyou.viewModel.BaseViewModel.Companion.launchOnBackground
import com.ins.boostyou.viewModel.ComposeNavigationViewModel
import com.ins.boostyou.viewModel.InAppPurchaseViewModel
import com.ins.boostyou.viewModel.MainActivityViewModel
import kotlinx.coroutines.launch

@Composable
fun MorePage(
    inAppPurchaseViewModel: InAppPurchaseViewModel,
    mainActivityViewModel: MainActivityViewModel,
    composeNavigationViewModel: ComposeNavigationViewModel
) {
    var isBuyCoinsSelected by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 32.dp, top = 16.dp, end = 32.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {

    }

    if (isBuyCoinsSelected) {
        PaymentPurchaseDialog(inAppPurchaseViewModel,
            onDismissRequest = {
                isBuyCoinsSelected = false
            },
            onConfirmation = {

            }
        )
    }



    Column(
        Modifier
            .background(Color.White)
    ) {
//        Button(onClick = {
//           // inAppPurchaseViewModel.launchInAppBillingFlow(activity,"com.boost.coin30")
//            inAppPurchaseViewModel.launchInAppBillingFlow(activity,"android.test.purchased")
//        }) {
//        }
        DropdownUsers(mainActivityViewModel)

        Divider(thickness = 4.dp, color = Color(0xFFD9DDE1))
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Buy Coins",
                modifier = Modifier
                    .clickable {
                        coroutineScope.launch {
                            inAppPurchaseViewModel.getPackageDetails()
                        }
                        isBuyCoinsSelected = true
                    }
                    .padding(horizontal = 32.dp, vertical = 24.dp)
                    .fillMaxWidth()
            )
        }
        Divider(thickness = 4.dp, color = Color(0xFFD9DDE1))
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            val coroutineScope = rememberCoroutineScope()
            Text(text = "Option",
                //style = TextStyle(
                //fontSize = 16.sp
                //  ),
                fontSize = 16.sp,
                modifier = Modifier
                    .clickable {

                    }
                    .padding(horizontal = 32.dp, vertical = 24.dp)
                    .fillMaxWidth()
            )
        }
        Divider(thickness = 4.dp, color = Color(0xFFD9DDE1))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Log Out",
                modifier = Modifier
                    .clickable {
                        composeNavigationViewModel.selectedTabItem = 0
                        coroutineScope.launch {
                            mainActivityViewModel.logOut()
                        }
                    }
                    .padding(horizontal = 32.dp, vertical = 24.dp)
                    .fillMaxWidth()
            )
        }
        Divider(thickness = 4.dp, color = Color(0xFFD9DDE1))
    }
}

@Composable
fun DropdownUsers(mainActivityViewModel: MainActivityViewModel) {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val userList = FileDataUtils.getUserNameList(context)?.toMutableList()

    var selectedIndex by remember { mutableIntStateOf(0) }

    Box(modifier = Modifier
        .fillMaxWidth()
        .noRippleClickable {
            expanded = true
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 26.dp)
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
                shape = RoundedCornerShape(72.dp),
                modifier = Modifier.padding(start = 12.dp, bottom = 12.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(mainActivityViewModel.userData.profilePicUrl)
                        .error(R.drawable.ic_account)
                        .crossfade(false)
                        .build(),
                    contentDescription = "",
                    modifier = Modifier
                        .size(72.dp)
                        .background(Color.Transparent),
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                modifier = Modifier.padding(start = 12.dp, top = 12.dp),
                text = if (mainActivityViewModel.userData.userName.orEmpty()
                        .isNotEmpty()
                ) "@" + mainActivityViewModel.userData.userName.orEmpty() else ""
            )

            Image(
                painter = painterResource(id = R.drawable.ic_arrow_down_fill),
                contentDescription = "",
                Modifier
                    .size(32.dp, 32.dp)
                    .padding(start = 12.dp, top = 12.dp)
            )
        }
        if (userList.isNullOrEmpty()) {
            return
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            userList.forEachIndexed { index, s ->
                if (mainActivityViewModel.userData.userName == s) {
                    return@DropdownMenu
                }
                DropdownMenuItem(
                    text = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                modifier = Modifier.padding(start = 12.dp),
                                text = "@$s",
                                style = TextStyle(fontSize = 18.sp)
                            )
                            val context = LocalContext.current
                            Box(
                                modifier = Modifier
                                    .padding(end = 12.dp)
                                    .noRippleClickable {
                                        Log.d("dwd", "LogOut user - $s")
                                        FileDataUtils.removeAccountFromSavedList(s, context)
                                        expanded = false
                                    },
                            ) {
                                Image(
                                    painter = painterResource(id = com.google.android.material.R.drawable.ic_m3_chip_close),
                                    contentDescription = "",
                                )
                            }

                        }
                    },
                    onClick = {
                        selectedIndex = index
                        expanded = false
                        mainActivityViewModel.launchOnBackground {
                            mainActivityViewModel.requestDataFromNewJson(
                                s,
                                saveUserName = true,
                                showLoading = true
                            )
                        }
                    },
                )
            }
        }
    }
}