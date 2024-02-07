package com.ins.boostyou.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ins.boostyou.viewModel.MainActivityViewModel


@Composable
fun SelectedFollowersSection(mainActivityViewModel: MainActivityViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .background(Color(0x94F8F8F8))
            .padding(bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Followers", fontSize = 15.sp, color = Color(0xFF262627))
        Text(
            text = mainActivityViewModel.userData.followedByCount.toString(),
            fontSize = 52.sp,
            fontWeight = FontWeight(540),
            color = Color(0xFF262627)
        )
    }
}