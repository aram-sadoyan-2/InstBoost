package com.ins.boostyou.model.response.boostyou

import com.google.gson.annotations.SerializedName

data class UserInfo(
    val userName: String? = "",
    val coinsCount: Int? = -1,
    @SerializedName("Id") val id: Int? = -1,
    val lastUpdate: String? = "",
)