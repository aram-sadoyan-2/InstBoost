package com.ins.boostyou.model.response.boostyou

import com.google.gson.annotations.SerializedName
import com.ins.boostyou.model.response.BaseResponse

data class UserInfo(
    @SerializedName("deviceId") val deviceId: String? = "",
    @SerializedName("userName") val userName: String? = "",
    @SerializedName("coinsCount") val coinsCount: Int = 0,
    @SerializedName("Id") val id: Int? = -1,
    @SerializedName("lastUpdate") val lastUpdate: String? = "",
) : BaseResponse()