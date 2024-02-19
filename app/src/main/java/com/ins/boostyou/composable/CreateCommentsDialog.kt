package com.ins.boostyou.composable

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ins.boostyou.R
import com.ins.boostyou.viewModel.InAppPurchaseViewModel

@Composable
fun CreateCommentsDialog(
    inAppPurchaseViewModel: InAppPurchaseViewModel,
    popupTitle: String? = null,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 12.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            DynamicTextFieldScreen(onDismissRequest = {
                onDismissRequest()
            })
        }
    }
}

@Composable
fun DynamicTextFieldScreen(onDismissRequest: () -> Unit) {
    var textFieldList by remember { mutableStateOf(listOf("")) }
    Column(verticalArrangement = Arrangement.SpaceBetween) {
        Text(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp),
            text = "Create Comments for adding",
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 4.dp)
                .weight(weight = 1f, fill = false)
                .verticalScroll(rememberScrollState()),

            ) {
            DynamicTextFieldList(
                textFieldList,
                onChange = {
                    textFieldList = it
                })
        }
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(18.dp)
                .fillMaxWidth()
                .clickable {
                    Log.d("dwd", "COMMNET $textFieldList")
                }) {
            Button(
                onClick = {
                    onDismissRequest()
                }) {
                Text("Cancel")
            }
            Button(
                onClick = {

                }) {
                Text("Confirm")
            }
        }
    }

}

@Composable
fun DynamicTextFieldList(
    textFieldList: List<String>,
    onChange: (List<String>) -> Unit
) {
    Column {
        textFieldList.forEachIndexed { index, text ->
            TextField(
                value = text,
                onValueChange = {
                    onChange(textFieldList.toMutableList().apply { set(index, it) })
                },
                label = { Text("Comment ${index.plus(1)}") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
            )
        }
        IconButton(
            onClick = {
                onChange(textFieldList + "")
            },
            modifier = Modifier
                .size(48.dp)
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "Add TextField",
            )
        }
    }
}
