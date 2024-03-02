package com.ins.boostyou.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.ins.boostyou.viewModel.BaseViewModel.Companion.launchOnBackground
import com.ins.boostyou.viewModel.MainActivityViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserNameInputSection(mainActivityViewModel: MainActivityViewModel) {
    var text by rememberSaveable { mutableStateOf("") }
    Column(
        Modifier
            .clickable {

            }
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Search for account", modifier = Modifier.padding(vertical = 14.dp))
        val keyboardController = LocalSoftwareKeyboardController.current
        TextField(
            modifier = Modifier.padding(bottom = 16.dp).clip(RoundedCornerShape(34.dp)),
            value = text,
            onValueChange = {
                text = it
            },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide()
                    mainActivityViewModel.launchOnBackground {
                        mainActivityViewModel.requestDataFromNewJson(
                            userName = text,
                            saveUserName = true,
                            showLoading = true
                        )
                    }
                }
            ),
            label = { Text("Search for account") }
        )
    }
}