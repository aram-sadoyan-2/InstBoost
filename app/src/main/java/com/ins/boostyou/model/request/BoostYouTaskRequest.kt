package com.ins.boostyou.model.request


data class BoostYouTaskRequest(
    var taskType: Int? = null,
    var serviceUrl: String? = null,
    var quality: Int? = null,
    var count: Int? = null,
    var price: Int? = null,
    var userName: String? = null,
    var comments: List<String>? = null,
)