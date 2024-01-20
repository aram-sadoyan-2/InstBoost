package com.ins.boostyou.model.response

data class UserData(
    val userName: String? = "",
    val fullName: String? = "",
    val userMedia: UserMedia? = null
)

data class UserMedia(
    val pageInfo : PageInfo2? = null,
    val userMediaInfoList: List<UserMediaInfoList>? = emptyList()
)
data class UserMediaInfoList(
    val id:String? = ""
)
