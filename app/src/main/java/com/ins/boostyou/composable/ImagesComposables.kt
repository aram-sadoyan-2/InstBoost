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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ins.boostyou.R
import com.ins.boostyou.model.response.ImagesAndInfoEntity

@Preview
@Composable
fun ImagesContainer() {
    val listOfItems = makeObject()
    LazyRow {
        items(listOfItems) { item ->
            SingleImage(item)
        }
    }
}

fun makeObject(): List<ImagesAndInfoEntity> {
    val data = """
        [
    {
        "image_url": "https://scontent.fevn7-1.fna.fbcdn.net/v/t39.30808-6/333040046_3439834242958892_6701204803101473128_n.jpg?_nc_cat=108&ccb=1-7&_nc_sid=efb6e6&_nc_ohc=yEL_xTOvTzAAX9SVIq3&_nc_ht=scontent.fevn7-1.fna&oh=00_AfBaHgk3eiKqej3sJtM4QTcrxSYL7SmTSfo_KXuR0iFTkw&oe=65B0598A",
        "likes_count": 65,
        "comments_count": 43
    },
    {
        "image_url": "https://images.pexels.com/photos/268533/pexels-photo-268533.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
        "likes_count": 21,
        "comments_count": 98
    },
    {
        "image_url": "https://images.unsplash.com/photo-1541963463532-d68292c34b19?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8M3x8fGVufDB8fHx8fA%3D%3D",
        "likes_count": 91,
        "comments_count": 11
    },
    {
        "image_url": "https://thumbs.dreamstime.com/b/environment-earth-day-hands-trees-growing-seedlings-bokeh-green-background-female-hand-holding-tree-nature-field-gra-130247647.jpg",
        "likes_count": 21,
        "comments_count": 98
    },
    {
        "image_url": "https://scontent.fevn7-1.fna.fbcdn.net/v/t39.30808-6/333040046_3439834242958892_6701204803101473128_n.jpg?_nc_cat=108&ccb=1-7&_nc_sid=efb6e6&_nc_ohc=yEL_xTOvTzAAX9SVIq3&_nc_ht=scontent.fevn7-1.fna&oh=00_AfBaHgk3eiKqej3sJtM4QTcrxSYL7SmTSfo_KXuR0iFTkw&oe=65B0598A",
        "likes_count": 65,
        "comments_count": 43
    },
    {
        "image_url": "https://images.unsplash.com/photo-1541963463532-d68292c34b19?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8M3x8fGVufDB8fHx8fA%3D%3D",
        "likes_count": 91,
        "comments_count": 11
    }
]
    """

    val gson = Gson()
    val itemType = object : TypeToken<List<ImagesAndInfoEntity>>() {}.type
    return gson.fromJson(data, itemType)
}


@Composable
fun SingleImage(item: ImagesAndInfoEntity) {
    Card(
        modifier = cardViewModifier,
        shape = RoundedCornerShape(24.dp),
        colors = cardColors(containerColor = Color.White)
    ) {
        AsyncImage(
            model = item.imageUrl,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(1.0f)
                .height(125.dp)
        )
        Row {
            LikeCountComposable(item.likesCount.toString(), R.drawable.heart)
            LikeCountComposable(item.commentsCount.toString(), R.drawable.comment)
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