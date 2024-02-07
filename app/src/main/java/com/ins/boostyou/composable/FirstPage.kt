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
import androidx.compose.ui.tooling.preview.Preview
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
    when (composeNavigationViewModel.selectedTabItem) {
        0, 1, 2 -> {
            //val scroll = rememberScrollState()
            Column(
                Modifier
                    .background(Color.White)
            ) {
                if (mainActivityViewModel.userData.userState == UserState.SIGNED_IN) {
                    UserInfoSection(
                        composeNavigationViewModel = composeNavigationViewModel,
                        mainActivityViewModel = mainActivityViewModel,
                    )
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
                    0 -> LikesSection()
                    1 -> FollowersSection()
                    2 -> CommentsSection()
                }
            }
        }

        3 -> {
            Log.d("dwd", "KHJBHN 3")
            MorePage(inAppPurchaseViewModel, mainActivityViewModel)
        }
    }

}