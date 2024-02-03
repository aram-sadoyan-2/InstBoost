package com.ins.boostyou.model.response

data class UserData(
    val id: String? = "",
    val userName: String? = "",
    val fullName: String? = "",
    val profilePicUrl: String? = "",
    val profilePicUrlHd: String? = "",
    val followingCount: Long? = 0,
    val followedByCount: Long? = 0,
    val userMedia: UserMedia? = null,
    val userState: UserState = UserState.DOES_NOT_EXIST,
)

data class UserMedia(
    val pageInfo: PageInfo2? = null,
    val userMediaInfoList: List<UserMediaInfoList>? = emptyList()
)

data class UserMediaInfoList(
    val id: String? = "",
    val displayUrl: String? = "",
    val thumbnailSrcUrl: String? = "",
    val likeCount: Long? = 0,
    val likeMediaPreviewCount: Long? = 0,
    val commentCount: Long? = 0,
    val mediaPreview: String? = "",
    val typeName: String? = "",
    val thumbnailResources: List<ThumbnailResource>? = null,
    val isVideo: Boolean? = false
)

enum class UserState {
    SIGNED_IN,
    DOES_NOT_EXIST
}
