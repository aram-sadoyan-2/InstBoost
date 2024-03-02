@file:Suppress("NAME_SHADOWING")

package com.ins.boostyou.composable

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
    val context = LocalContext.current
    when (mainActivityViewModel.showPopupType) {
        AlertPopupType.REMOVE_ACCOUNT -> {
            AlertDialogSample(viewModel = mainActivityViewModel,
                title = "Are You Sure?",
                onDismissRequest = {
                    mainActivityViewModel.showPopupType = AlertPopupType.NONE
                },
                onConfirmation = {
                    mainActivityViewModel.removeCurrentAccount(context)
                    mainActivityViewModel.logOut()
                    composeNavigationViewModel.selectedTabItem = 0
                })
        }

        AlertPopupType.LIKE,
        AlertPopupType.FOLLOWER -> {
            AlertDialogSample(viewModel = mainActivityViewModel,
                title = "Confirm ${mainActivityViewModel.boostYouTaskRequest.count} ${mainActivityViewModel.showPopupType.extension} Promotion",
                onDismissRequest = {
                    mainActivityViewModel.showPopupType = AlertPopupType.NONE
                },
                onConfirmation = {
                    Log.d("dwd", "OnConfirmation for ${mainActivityViewModel.showPopupType.name}")
                    mainActivityViewModel.requestTask(context)
                    mainActivityViewModel.showPopupType = AlertPopupType.NONE
                })
        }

        AlertPopupType.COMMENT -> {
            CreateCommentsDialog(inAppPurchaseViewModel,
                onDismissRequest = {
                    mainActivityViewModel.showPopupType = AlertPopupType.NONE
                },
                onConfirmation = {
                    Log.d("dwd", "Comment Confirm $it")
                    mainActivityViewModel.boostYouTaskRequest.comments = it
                    mainActivityViewModel.requestTask(context)
                })
        }

        AlertPopupType.NO_ENOUGH_COIN -> {
            inAppPurchaseViewModel.getPackageDetails()
            PaymentPurchaseDialog(inAppPurchaseViewModel,
                onDismissRequest = {
                    mainActivityViewModel.showPopupType = AlertPopupType.NONE
                },
                onConfirmation = {
                }
            )
        }

        AlertPopupType.NO_IMAGE_SELECTED -> {
            val context = LocalContext.current
            Toast.makeText(
                context,
                mainActivityViewModel.showPopupType.extension,
                Toast.LENGTH_SHORT
            )
                .show()
            mainActivityViewModel.showPopupType = AlertPopupType.NONE
        }

        else -> {}
    }
    when {
        mainActivityViewModel.isLoading() -> LoadingView()
        mainActivityViewModel.isDoneIcon() -> DoneIconComposable(mainActivityViewModel = mainActivityViewModel)
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
                            UserMainInfoSection(mainActivityViewModel)
                        } else {
                            UserNameInputSection(mainActivityViewModel)
                        }
                        Divider(thickness = 1.dp, color = Color(0xFFD9DDE1))
                        if (composeNavigationViewModel.selectedTabItem == 1) {
                            SelectedFollowersSection(mainActivityViewModel = mainActivityViewModel)
                        } else if (mainActivityViewModel.userData.userMedia != null
                            && mainActivityViewModel.userData.userMedia?.userMediaInfoList?.size != 0
                        ) {
                            ImagesContainer(mainActivityViewModel = mainActivityViewModel)
                        }
                        Divider(thickness = 1.dp, color = Color(0xFFD9DDE1))
                        when (composeNavigationViewModel.selectedTabItem) {
                            0 -> TabScreenPriceLikes(mainActivityViewModel)
                            1 -> TabScreenPriceFollowers(mainActivityViewModel)
                            2 -> CommentsSection(mainActivityViewModel)
                        }
                    }
                }
            }
        }
    }
}



