package com.ins.boostyou.model.response

import com.google.gson.annotations.SerializedName

data class ImagesAndInfoEntity(
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("likes_count")
    val likesCount: Int,
    @SerializedName("comments_count")
    val commentsCount: Int
)
