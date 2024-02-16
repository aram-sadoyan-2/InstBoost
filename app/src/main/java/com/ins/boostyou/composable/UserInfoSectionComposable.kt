package com.ins.boostyou.composable

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ins.boostyou.viewModel.ComposeNavigationViewModel
import com.ins.boostyou.viewModel.MainActivityViewModel

@Composable
fun UserMainInfoSection(
    composeNavigationViewModel: ComposeNavigationViewModel,
    mainActivityViewModel: MainActivityViewModel
) {
    Box {
        UserInfoSection(mainActivityViewModel)
    }
}

@Composable
private fun UserInfoSection(mainActivityViewModel: MainActivityViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
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
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 12.dp, start = 32.dp)
        ) {
            FollowersCount(
                "posts",
                mainActivityViewModel.userData.userMedia?.userMediaInfoList?.size?.toLong(),
                modifier = Modifier.weight(1f)
            )
            FollowersCount(
                "followers",
                mainActivityViewModel.userData.followedByCount,
                modifier = Modifier.weight(1f)
            )
            FollowersCount(
                "following",
                mainActivityViewModel.userData.followingCount,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun FollowersCount(bottomText: String, topText: Long?, modifier: Modifier) {
    Column(
        modifier = Modifier
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = topText.toString())
        Text(text = bottomText)
    }
}