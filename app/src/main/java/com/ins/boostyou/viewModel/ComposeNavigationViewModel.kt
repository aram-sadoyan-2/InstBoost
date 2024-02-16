package com.ins.boostyou.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.ins.boostyou.model.response.UserData
import com.ins.boostyou.model.response.UserState
import com.ins.boostyou.module.ProfDispatchers

class ComposeNavigationViewModel(
    dispatchers: ProfDispatchers
) : BaseViewModel(dispatchers) {


    var addCoinSelected by mutableStateOf(false)


}