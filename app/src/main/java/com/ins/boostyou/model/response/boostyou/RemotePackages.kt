package com.ins.boostyou.model.response.boostyou

import com.google.gson.annotations.SerializedName
import com.ins.boostyou.model.response.BaseResponse


data class RemotePackages(
    @SerializedName("data") val remotePackages: List<RemotePackageItem>? = emptyList(),
) : BaseResponse()

data class RemotePackageItem(
    @SerializedName("count") val count: Int? = -1,
    @SerializedName("price") val price: Int? = -1,
    @SerializedName("id_sdk") val idSdk: String? = "",
    @SerializedName("id") val id: Int? = -1,
    @SerializedName("package_id") val packageId: String? = "",

    @SerializedName("name") val name: String? = "",
) : BaseResponse()
