package com.ins.boostyou.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ins.boostyou.viewModel.MainActivityViewModel
import kotlinx.coroutines.delay

@Composable
fun DoneIconComposable(modifier: Modifier = Modifier, mainActivityViewModel: MainActivityViewModel,){
    var show by remember { mutableStateOf(true) }
    LaunchedEffect(key1 = Unit){
        delay(1000)
        show = false
        mainActivityViewModel.loadingState = null
    }
    if (show) {
        Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Icon(imageVector = Icons.Default.Done, contentDescription = "", tint = Color(0xFFD1384A), modifier = Modifier.size(64.dp))
        }
    }
}