package com.ins.boostyou.model.response

import com.google.gson.annotations.SerializedName

data class InstAccessTokenResponseModel(
    @SerializedName("error_message") val errorType: String = "",
    @SerializedName("error") val error: String = "",
    @SerializedName("code") val code: String = "",
    @SerializedName("redirect_uri") val redirect_uri: String = "",
    @SerializedName("access_token") val accessToken: String = "",
    @SerializedName("user_id") val userId: Long = 0,

    //for long-live-access-token
    @SerializedName("token_type") val tokenType: String = "",
    @SerializedName("expires_in") val expiresInSeconds: Long = 0,

    //userId
    @SerializedName("id") val id: String = "",
    @SerializedName("username") val userNam: String = "",
)