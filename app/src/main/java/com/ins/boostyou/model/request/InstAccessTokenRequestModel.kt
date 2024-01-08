package com.ins.boostyou.model.request

import com.google.gson.annotations.SerializedName

data class InstAccessTokenRequestModel(
    @SerializedName("client_id") val client_id: String = "",
    @SerializedName("client_secret") val client_secret: String = "",
    @SerializedName("grant_type") val grant_type: String = "",
    @SerializedName("redirect_uri") val redirect_uri: String = "",
    @SerializedName("code") val code: String = "",


    @SerializedName("access_token") val shortLiveAccessToken: String = ""
)