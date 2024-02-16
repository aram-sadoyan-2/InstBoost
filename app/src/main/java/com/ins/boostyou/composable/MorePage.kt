package com.ins.boostyou.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ins.boostyou.R
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
        PaymentPurchaseDialogNew(inAppPurchaseViewModel,
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
        DropdownDemo(mainActivityViewModel)

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
fun DropdownDemo(mainActivityViewModel: MainActivityViewModel) {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("A", "B", "C", "D", "E", "F")
    var selectedIndex by remember { mutableStateOf(0) }
    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            expanded = true
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                shape = RoundedCornerShape(72.dp),
                modifier = Modifier.padding(start = 12.dp, bottom = 12.dp)
            ) {
                AsyncImage(
                    model = mainActivityViewModel.userData.profilePicUrl,
                    contentDescription = "image description",
                    modifier = Modifier.size(72.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Text(text = mainActivityViewModel.userData.userName.toString())

            Image(
                painter = painterResource(id = R.drawable.ic_arrow_down_fill),
                contentDescription = "",
                Modifier
                    .size(32.dp, 32.dp)
                    .padding(start = 12.dp)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
            //.background(
            // Color.Red
            // )
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(
                    text = {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_arrow_down_fill),
                                contentDescription = "wdwf"
                            )
                            Text(text = s)
                        }
                    },
                    onClick = {
                        selectedIndex = index
                        expanded = false
                    },
                )
            }
        }
    }
}