package com.ins.boostyou.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun FistPage() {
    val scroll = rememberScrollState()
    Column(
        Modifier
            .background(Color.White)
    ) {
        UserInfoSection()
        Divider(thickness = 1.dp, color = Color(0xFFD9DDE1))
        Spacer(modifier = Modifier.height(16.dp))
        ImagesContainer()
        Spacer(modifier = Modifier.height(20.dp))
        Divider(thickness = 1.dp, color = Color(0xFFD9DDE1))
        Spacer(modifier = Modifier.height(20.dp))
        Section()
    }
}