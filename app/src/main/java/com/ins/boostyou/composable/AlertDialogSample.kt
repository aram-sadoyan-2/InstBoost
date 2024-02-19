package com.ins.boostyou.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ins.boostyou.constants.enum.AlertPopupType
import com.ins.boostyou.viewModel.MainActivityViewModel

@Composable
fun AlertDialogSample(
    viewModel: MainActivityViewModel,
    title: String = "",
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    MaterialTheme {
        Column {
            AlertDialog(
                onDismissRequest = {
                    viewModel.showPopupType = AlertPopupType.NONE
                },
                title = {
                    Text(text = title)
                },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.showPopupType = AlertPopupType.NONE
                            onConfirmation()
                        }) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            viewModel.showPopupType = AlertPopupType.NONE
                            onDismissRequest()
                        }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}
