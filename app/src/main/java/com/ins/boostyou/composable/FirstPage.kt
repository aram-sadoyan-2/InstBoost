package com.ins.boostyou.composable

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ins.boostyou.constants.enum.AlertPopupType
import com.ins.boostyou.model.response.UserState
import com.ins.boostyou.viewModel.ComposeNavigationViewModel
import com.ins.boostyou.viewModel.InAppPurchaseViewModel
import com.ins.boostyou.viewModel.MainActivityViewModel

@Composable
fun FistPage(
    composeNavigationViewModel: ComposeNavigationViewModel,
    mainActivityViewModel: MainActivityViewModel,
    inAppPurchaseViewModel: InAppPurchaseViewModel,
) {
   // val coroutineScope = rememberCoroutineScope()
    when (mainActivityViewModel.showPopupType) {
        AlertPopupType.LIKE,
        AlertPopupType.FOLLOWER,
        AlertPopupType.COMMENT -> {
            AlertDialogSample(viewModel = mainActivityViewModel,
                title = "Confirm ${mainActivityViewModel.showPopupType.name} Promotion",
                subTitle = "Promote Like Subtitle",
                onDismissRequest = {
                    mainActivityViewModel.showPopupType = AlertPopupType.NONE
                },
                onConfirmation = {
                    Log.d("dwd","OnConfirmation for ${mainActivityViewModel.showPopupType.name}")
                    mainActivityViewModel.requestTask()
                    mainActivityViewModel.showPopupType = AlertPopupType.NONE
                })
        }
        AlertPopupType.NO_ENOUGH_COIN -> {
            inAppPurchaseViewModel.getPackageDetails()
            PaymentPurchaseDialogNew(inAppPurchaseViewModel,
                onDismissRequest = {
                    mainActivityViewModel.showPopupType = AlertPopupType.NONE
                },
                onConfirmation = {

                }
            )
        }
        else -> {}
    }

    when {
        mainActivityViewModel.isLoading() -> LoadingView()
        else -> {
            when (composeNavigationViewModel.selectedTabItem) {
                3 -> {
                    MorePage(
                        inAppPurchaseViewModel,
                        mainActivityViewModel,
                        composeNavigationViewModel
                    )
                }

                else -> {
                    Column(
                        Modifier
                            .background(Color.White)
                            .fillMaxWidth()
                    ) {
                        TopCoinInfo(mainActivityViewModel, inAppPurchaseViewModel)
                        if (mainActivityViewModel.userData.userState == UserState.SIGNED_IN) {
                            UserMainInfoSection(composeNavigationViewModel, mainActivityViewModel)
                        } else {
                            UserNameInputSection(mainActivityViewModel)
                        }
                        Divider(thickness = 1.dp, color = Color(0xFFD9DDE1))
                        if (composeNavigationViewModel.selectedTabItem == 1) {
                            SelectedFollowersSection(mainActivityViewModel = mainActivityViewModel)
                        } else {
                            ImagesContainer(mainActivityViewModel = mainActivityViewModel)
                        }
                        Divider(thickness = 1.dp, color = Color(0xFFD9DDE1))
                        when (composeNavigationViewModel.selectedTabItem) {
                            0 -> TabScreenPriceLikes(mainActivityViewModel)
                            1 -> TabScreenPriceFollowers(mainActivityViewModel)
                            2 -> CommentsSection()
                        }
                    }
                }
            }
        }
    }
}



