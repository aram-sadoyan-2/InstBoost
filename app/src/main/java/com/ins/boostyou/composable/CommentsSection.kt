package com.ins.boostyou.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ins.boostyou.R
import com.ins.boostyou.model.response.boostyou.LikesPriceItem
import com.ins.boostyou.viewModel.MainActivityViewModel

@Preview
@Composable
fun CommentsSection(mainActivityViewModel: MainActivityViewModel) {
    Column(
        Modifier
            .padding(bottom = 54.dp)
            .background(Color.White)
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
    ) {
        mainActivityViewModel.commentsCoast.data?.toMutableList()?.let { likesPriceItems ->
            LazyColumn() {
                items(likesPriceItems) {
                    CommentsSectionItem(it)
                }
            }
        }
    }
}

@Preview
@Composable
fun CommentsSectionItem(likePriceItem: LikesPriceItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 32.dp, top = 16.dp, end = 32.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_comment),
            contentDescription = "",
            Modifier.size(32.dp, 32.dp)
        )
        Text(text = "X", Modifier.padding(horizontal = 16.dp), textAlign = TextAlign.Center)
        Text(
            text = likePriceItem.count.toString(),
            modifier = Modifier.weight(1f),
            style = TextStyle(
                fontSize = 18.sp
            )
        )
        Button(
            onClick = {
                /*TODO*/
            },
            modifier = Modifier.height(30.dp),
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(1.dp, Color(0XFF6A698D)),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color(0XFF6A698D),
                containerColor = Color.White
            )
        ) {
            Text(
                text = likePriceItem.price.toString(), style = TextStyle(
                    fontSize = 13.sp
                )
            )
        }
    }
}