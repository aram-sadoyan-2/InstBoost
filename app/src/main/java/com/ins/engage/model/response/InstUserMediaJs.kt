package com.ins.engage.model.response

import com.google.gson.annotations.SerializedName

data class InstUserMediaJs(
    val data: List<DataResponse>,
    val paging: Paging
)

data class DataResponse(
    val id: String,
    val username: String,
    val permalink: String,
    @SerializedName("media_type") val mediaType: String,
)

data class Cursors(
    val before: String,
    val after: String
)

data class Paging(
    val cursors: Cursors
)
