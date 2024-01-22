package com.ins.boostyou.model.response.boostyou

import com.google.gson.annotations.SerializedName

data class RemotePackages(
    @SerializedName("count") val count: Int? = -1,
    @SerializedName("price") val price: Int? = -1,
    @SerializedName("id_sdk") val idSdk: String? = "",
    @SerializedName("id") val id: Int? = -1,
    @SerializedName("package_id") val packageId: String? = "",
)