package com.ins.boostyou.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.airbnb.lottie.LottieDrawable
import com.airbnb.lottie.LottieDrawable.INFINITE
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ins.boostyou.R
import com.ins.boostyou.model.response.UserMediaInfoList
import com.ins.boostyou.viewModel.MainActivityViewModel
import kotlinx.coroutines.coroutineScope

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagesContainer(
    mainActivityViewModel: MainActivityViewModel,
) {
    mainActivityViewModel.userData.userMedia?.userMediaInfoList?.let { userMediaInfo ->
        val newList = userMediaInfo.toMutableList()
        val pagerState = rememberPagerState { newList.size }
        val firstItem = remember { derivedStateOf { pagerState.currentPage } }
        newList.add(0, null)
        newList.add(newList.lastIndex + 1, null)

        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fixed(156.dp),
            contentPadding = PaddingValues(start = 64.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
        ) {
            SingleImage(item = newList[it], firstItem.value, it)
        }
    }
}


@Composable
fun SingleImage(item: UserMediaInfoList?, firstItem: Int, index: Int) {
    if (item == null) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.swipe_animation))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(end = 4.dp)
                .fillMaxWidth(1f)
        ) {
            if (firstItem == 0) {
                Text(
                    text = "Swipe to Select Post",
                    modifier = Modifier.padding(bottom = 8.dp),
                    style = TextStyle(
                        color = Color.Black,
                        fontStyle = FontStyle.Italic,
                        fontSize = TextUnit(14f, TextUnitType.Sp)
                    )
                )
                LottieAnimation(
                    composition = composition,
                    speed = 2.5f,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier
                        .height(170.dp)
                        .padding(horizontal = 32.dp)
                        .padding(bottom = 32.dp)
                )
            }
        }
    } else {
        Card(
            modifier = if (firstItem == index) selectedCardView else cardViewModifier,
            shape = RoundedCornerShape(24.dp),
            colors = cardColors(containerColor = Color.White)
        ) {
            AsyncImage(
                model = item.displayUrl,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .height(if (firstItem == index) 145.dp else 110.dp)
            )
            Row {
                LikeCountComposable(item.likeCount.toString(), R.drawable.heart)
                LikeCountComposable(item.commentCount.toString(), R.drawable.comment)
            }
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
    .width(145.dp)
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

val selectedCardView = Modifier
    .wrapContentHeight()
    .padding(end = 4.dp)
    .border(
        width = 1.dp,
        color = Color(0xFFF05161),
        shape = RoundedCornerShape(24.dp),
    )
    .shadow(
        elevation = 8.dp,
        spotColor = Color(0xFFF05161),
        ambientColor = Color(0xFFF05161),
        shape = RoundedCornerShape(24.dp)
    )