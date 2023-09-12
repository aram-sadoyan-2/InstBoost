package com.ins.engage.model.request

import com.google.gson.annotations.SerializedName

data class InstAccessTokenRequestModel(
    @SerializedName("code") val code: String = "",
)