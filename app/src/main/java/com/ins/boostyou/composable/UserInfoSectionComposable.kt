package com.ins.boostyou.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Preview
@Composable
fun UserInfoSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Card(
            shape = RoundedCornerShape(72.dp),
            modifier = Modifier.padding(start = 12.dp, top = 12.dp, bottom = 12.dp)
        ) {
            AsyncImage(
                model = "https://scontent.fevn7-1.fna.fbcdn.net/v/t39.30808-6/333040046_3439834242958892_6701204803101473128_n.jpg?_nc_cat=108&ccb=1-7&_nc_sid=efb6e6&_nc_ohc=yEL_xTOvTzAAX9SVIq3&_nc_ht=scontent.fevn7-1.fna&oh=00_AfBaHgk3eiKqej3sJtM4QTcrxSYL7SmTSfo_KXuR0iFTkw&oe=65B0598A",
                contentDescription = "image description",
                modifier = Modifier.size(72.dp),
                contentScale = ContentScale.Crop
            )
        }
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 12.dp, start = 32.dp)
        ) {
            FollowersCount("posts", "1230", modifier = Modifier.weight(1f))
            FollowersCount("followers", "930", modifier = Modifier.weight(1f))
            FollowersCount("followings", "130", modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun FollowersCount(bottomText: String, topText: String, modifier: Modifier) {
    Column(
        modifier = Modifier
            .padding(12.dp)
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = topText)
        Text(text = bottomText)
    }
}