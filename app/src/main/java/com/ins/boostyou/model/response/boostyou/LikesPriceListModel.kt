package com.ins.boostyou.model.response.boostyou

import com.google.gson.annotations.SerializedName
import com.ins.boostyou.model.response.BaseResponse

data class LikesPriceListModel(
    @SerializedName("data") val data: List<LikesPriceItem>? = emptyList(),
) : BaseResponse()

data class LikesPriceItem(
    @SerializedName("count") val count: Int = 0,
    @SerializedName("price") val price: Int? = -1,
) : BaseResponse()
