package com.ins.boostyou.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class BaseResponse(
    @SerializedName("status") val status: String? = "",
    @SerializedName("message") val message: String? = "",
    @SerializedName("taskId") val taskId: String? = "",
    @SerializedName("taskApiError") val taskError: String? = "",
//    @SerializedName("success") val success: Boolean? = false,
//    @SerializedName("access_token") val accessToken: String? = "",
//    @SerializedName("exists") val exists: Int? = -1,
) : Serializable