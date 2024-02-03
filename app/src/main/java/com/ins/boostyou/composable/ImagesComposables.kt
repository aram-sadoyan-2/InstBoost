package com.ins.boostyou.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ins.boostyou.R
import com.ins.boostyou.model.response.UserMediaInfoList
import com.ins.boostyou.viewModel.MainActivityViewModel

@Preview
@Composable
fun ImagesContainer(mainActivityViewModel: MainActivityViewModel) {
    LazyRow {
        mainActivityViewModel.userData.userMedia?.userMediaInfoList?.let {
            items(it) { item ->
                SingleImage(item)
            }
        }
    }
}

@Composable
fun SingleImage(item: UserMediaInfoList) {
    Card(
        modifier = cardViewModifier,
        shape = RoundedCornerShape(24.dp),
        colors = cardColors(containerColor = Color.White)
    ) {
        AsyncImage(
            model = item.displayUrl,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(1.0f)
                .height(125.dp)
        )
        Row {
            LikeCountComposable(item.likeCount.toString(), R.drawable.heart)
            LikeCountComposable(item.commentCount.toString(), R.drawable.comment)
        }
    }
}

@Composable
fun LikeCountComposable(count: String, icon: Int) {
    Row(modifier = Modifier.padding(start = 8.dp, bottom = 24.dp)) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "",
            modifier = Modifier.size(18.dp),
        )
        Text(
            text = count,
            modifier = Modifier.padding(start = 4.dp), style = TextStyle(
                fontSize = 13.sp,
                fontWeight = FontWeight(400),
                fontStyle = FontStyle.Italic,
            )
        )
    }
}

val cardViewModifier = Modifier
    .padding(4.dp)
    .width(160.dp)
    .wrapContentHeight()
    .border(
        width = 1.dp,
        color = Color(0xFFD9DDE1),
        shape = RoundedCornerShape(24.dp),
    )
    .shadow(
        elevation = 8.dp,
        spotColor = Color(0x63000000),
        ambientColor = Color(0x40000000),
        shape = RoundedCornerShape(24.dp)
    )