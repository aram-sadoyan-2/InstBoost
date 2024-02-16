package com.ins.boostyou.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.Indicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ins.boostyou.viewModel.MainActivityViewModel

@Composable
fun TabScreenPriceFollowers(viewModel: MainActivityViewModel) {
    var selectedTab by remember { mutableIntStateOf(0) }

    DisposableEffect(Unit) {
        onDispose { selectedTab = 0 }
    }
    val tabIndex = viewModel.tabIndex
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        TabRow(
            selectedTabIndex = selectedTab,
            contentColor = Color.Black,
            indicator = {
                Indicator(
                    modifier = Modifier.tabIndicatorOffset(it[selectedTab]),
                    height = 2.dp,
                    color = Color(0xFFF05161)
                )
            },
            containerColor = Color.White,
            ) {
            viewModel.tabs.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Text(
                            text = title,
                            color = if (selectedTab == index) Color(0xFFF05161) else Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Light
                        )
                    },
                    selected = tabIndex.value == index,
                    onClick = {
                        selectedTab = index
                    },
                )
            }
        }

        when (selectedTab) {
            0 -> FollowersSection(viewModel, selectedTab)
            1 -> FollowersSection(viewModel, selectedTab)
        }
    }
}