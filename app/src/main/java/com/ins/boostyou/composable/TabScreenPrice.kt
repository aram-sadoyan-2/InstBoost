package com.ins.boostyou.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.ins.boostyou.viewModel.MainActivityViewModel

var selectedTab by mutableStateOf(0)

@Composable
fun TabScreenPrice(viewModel: MainActivityViewModel) {
    DisposableEffect(Unit) {
        onDispose { selectedTab = 0 }
    }
    val tabIndex = viewModel.tabIndex
    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = selectedTab) {
            viewModel.tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex.value!! == index,
                    onClick = {
                        selectedTab = index
                    },
//                    icon = {
//                        when (index) {
//                            0 -> Row {
//
//                            }
//                            1 -> Icon(imageVector = Icons.Default.Info, contentDescription = null)
//                        }
//                    }
                )
            }
        }

        when (selectedTab) {
            0 -> LikesSection()
            1 -> FollowersSection()
        }

        //todo for swipe func use HorizontalPager
       // implementation "com.google.accompanist:accompanist-pager:0.21.2-beta"
       // implementation "com.google.accompanist:accompanist-pager-indicators:0.21.2-beta"

//        HorizontalPager( // 4.
//            count = tabTitles.size,
//            state = pagerState,
//        ) { tabIndex ->
//            Text(
//                tabIndex.toString(),
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color.White)
//            )
//        }
    }
}