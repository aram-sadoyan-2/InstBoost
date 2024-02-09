package com.ins.boostyou.composable

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ins.boostyou.model.response.UserState
import com.ins.boostyou.viewModel.ComposeNavigationViewModel
import com.ins.boostyou.viewModel.InAppPurchaseViewModel
import com.ins.boostyou.viewModel.MainActivityViewModel

@Composable
fun FistPage(
    composeNavigationViewModel: ComposeNavigationViewModel,
    mainActivityViewModel: MainActivityViewModel,
    inAppPurchaseViewModel: InAppPurchaseViewModel
) {
    when {
        mainActivityViewModel.isLoading() -> LoadingView()
        else -> {
            //todo move to another function
            when (composeNavigationViewModel.selectedTabItem) {
                3 -> {
                    MorePage(inAppPurchaseViewModel, mainActivityViewModel)
                }

                else -> {
                    Column(
                        Modifier.background(Color.White)
                    ) {
                        if (mainActivityViewModel.userData.userState == UserState.SIGNED_IN) {
                            UserInfoSection(composeNavigationViewModel, mainActivityViewModel)
                        } else {
                            Log.d("dwd", "Logout state")
                            UserNameInputSection()
                        }
                        Divider(thickness = 1.dp, color = Color(0xFFD9DDE1))
                        if (composeNavigationViewModel.selectedTabItem == 1) {
                            SelectedFollowersSection(mainActivityViewModel = mainActivityViewModel)
                        } else {
                            ImagesContainer(mainActivityViewModel = mainActivityViewModel)
                        }
                        Divider(thickness = 1.dp, color = Color(0xFFD9DDE1))
                        when (composeNavigationViewModel.selectedTabItem) {
                            0 -> TabScreenPrice(mainActivityViewModel)
                            1 -> FollowersSection()
                            2 -> CommentsSection()
                        }
                    }
                }
            }
        }
    }
}