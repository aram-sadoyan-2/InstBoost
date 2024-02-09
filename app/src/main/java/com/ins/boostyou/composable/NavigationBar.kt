package com.ins.boostyou.composable

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.ins.boostyou.viewModel.ComposeNavigationViewModel
import com.ins.boostyou.viewModel.InAppPurchaseViewModel
import com.ins.boostyou.viewModel.MainActivityViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun NavigationBar(
    inAppPurchaseViewModel: InAppPurchaseViewModel,
    composeNavigationViewModel: ComposeNavigationViewModel,
    mainActivityViewModel: MainActivityViewModel
) {
    // setting up the individual tabs
    val homeTab = TabBarItem(
        title = "Likes",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    )
    val alertsTab = TabBarItem(
        title = "Followers",
        selectedIcon = Icons.Filled.Notifications,
        unselectedIcon = Icons.Outlined.Notifications
    )
    val settingsTab = TabBarItem(
        title = "Comments",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )
    val moreTab = TabBarItem(
        title = "Settings",
        selectedIcon = Icons.Filled.List,
        unselectedIcon = Icons.Outlined.List
    )
    // creating a list of all the tabs
    val tabBarItems = listOf(homeTab, alertsTab, settingsTab, moreTab)

    //InstBoostTheme {
    // A surface container using the 'background' color from the theme
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Scaffold(
            bottomBar = { TabView(tabBarItems, composeNavigationViewModel) {} },
            modifier = Modifier.fillMaxHeight()
        ) {
            FistPage(composeNavigationViewModel, mainActivityViewModel, inAppPurchaseViewModel)
        }
    }
    // }
}

@Composable
fun TabView(
    tabBarItems: List<TabBarItem>,
    composeNavigationViewModel: ComposeNavigationViewModel,
    onTabSelect: (Int) -> Unit
) {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }

    NavigationBar(
        containerColor = Color.Transparent,
        modifier = Modifier.height(50.dp).then(socialProofCardBackgroundModifier),
    ) {
        tabBarItems.forEachIndexed { index, tabBarItem ->
            NavigationBarItem(
                selected = selectedTabIndex == index,
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterVertically),
                onClick = {
                    composeNavigationViewModel.selectedTabItem = index
                    selectedTabIndex = index
                    onTabSelect(index)
                    Log.d("dwd", "selectedTab indx= $index")
                },
                icon = {
                    TabBarIconView(
                        isSelected = selectedTabIndex == index,
                        selectedIcon = tabBarItem.selectedIcon,
                        unselectedIcon = tabBarItem.unselectedIcon,
                        title = tabBarItem.title,
                        badgeAmount = tabBarItem.badgeAmount,
                    )
                },
                colors = NavigationBarItemDefaults
                    .colors(
                        indicatorColor = Color.White
                    )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabBarIconView(
    isSelected: Boolean,
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector,
    title: String,
    badgeAmount: Int? = null
) {
    BadgedBox(badge = { TabBarBadgeView(badgeAmount) }) {
        Icon(
            imageVector = if (isSelected) selectedIcon else unselectedIcon,
            contentDescription = title,
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TabBarBadgeView(count: Int? = null) {
    if (count != null) {
        Badge {
            Text(count.toString())
        }
    }
}

data class TabBarItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeAmount: Int? = null
)

val socialProofCardBackgroundModifier = Modifier
    .background(
        Brush.linearGradient(
            colors = listOf(
                Color(0xFF00FFED),
                Color(0xFF19D7E7),
                Color(0xFF7F4AD9),
                Color(0xFFAF1FC6),
                Color(0x0DDF02C0),
                Color(0x0DDF02C0),
                Color(0x1AFFFFFF),
            ),
            start = Offset(0f, 10f),
        ),
        alpha = 0.1f,
    )