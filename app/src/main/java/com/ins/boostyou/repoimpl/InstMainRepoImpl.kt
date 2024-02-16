package com.ins.boostyou.repoimpl

import android.content.Context
import android.util.Log
import com.ins.boostyou.AppResult
import com.ins.boostyou.api.RetrofitPostServiceApi
import com.ins.boostyou.controller.FileDataUtils
import com.ins.boostyou.handleSuccess
import com.ins.boostyou.model.response.EdgeOwnerToTimelineMedia
import com.ins.boostyou.repository.InstMainRepo
import com.ins.boostyou.model.response.InstPrData
import com.ins.boostyou.model.response.UserData
import com.ins.boostyou.model.response.UserMedia
import com.ins.boostyou.model.response.UserMediaInfoList
import com.ins.boostyou.model.response.UserState
import com.ins.boostyou.model.response.boostyou.LikesPriceListModel
import com.ins.boostyou.utils.logD
import kotlin.math.log

class InstMainRepoImpl(
    private val api: RetrofitPostServiceApi,
    private val context: Context
) : InstMainRepo {

    override suspend fun getPostDataFromNewJson(
        userName: String?,
        saveUserName: Boolean?
    ): AppResult<UserData> {
        return try {
            val usrNme = userName ?: getUserNameFromPref()
            if (usrNme.isEmpty()) {
                return AppResult.Error(Exception("Username Does not exists"))
            }
            api.getPostDataFromNewJson(
                userName = usrNme,
                secFetchDest = "empty",
                secFetchMode = "cors",
                secFetchSite = "same-origin",
                appId = 936619743392459,
                claim = 0,
                requestedWith = "XMLHttpRequest"
            )?.let { response ->
                val userData = parseToUserData(response)
                if (saveUserName == true &&
                    !userData.id.isNullOrBlank() &&
                    !userData.userName.isNullOrBlank()
                ) {
                    FileDataUtils.saveUsNmAndId(context, userData.id, userData.userName)
                }
                handleSuccess(userData)
            } ?: run {
                Log.d("dwd", "getPostData Error response is null")
                AppResult.Error(Exception("empty data"))
            }
        } catch (e: Exception) {
            Log.d("dwd", "getPostDataFromNewJson Catch Error " + e.message)
            AppResult.Error(e)
        }
    }

    override fun logOutUser() {
        FileDataUtils.logoutUserFromLocal(context)
    }

    private fun getUserNameFromPref(): String {
        return FileDataUtils.getUsNameFromLocal(context)
    }


    private fun parseToUserData(response: InstPrData?): UserData {
        //contains User Media Info list
        val edgeOwnerToTimelineMedia = response?.data?.user?.edgeOwnerToTimelineMedia
        val userMedia = getUserMedia(edgeOwnerToTimelineMedia)
        response?.data?.user?.let { user ->
            return UserData(
                id = user.id,
                userName = user.username,
                fullName = user.fullName,
                profilePicUrl = user.profilePicUrl,
                profilePicUrlHd = user.profilePicUrlHd,
                followingCount = user.edgeFollow.count,
                followedByCount = user.edgeFollowedBy.count,
                userMedia = userMedia,
                userState = UserState.SIGNED_IN
            )
        } ?: run {
            return UserData()
        }
    }

    private fun getUserMedia(edgeOwnerToTimelineMedia: EdgeOwnerToTimelineMedia?): UserMedia {
        val pageInfo = edgeOwnerToTimelineMedia?.pageInfo
        val userMediaInfoList = mutableListOf<UserMediaInfoList>()
        edgeOwnerToTimelineMedia?.edges?.forEach { edge ->
            edge.node.let { node ->
                //contains video info
                //val videoUrl = node.videoUrl
                //....
                userMediaInfoList.add(
                    UserMediaInfoList(
                        id = node.id,
                        postUrl = "https://www.instagram.com/p/" + node.shortcode,
                        displayUrl = node.displayUrl,
                        thumbnailSrcUrl = node.thumbnailSrc,
                        likeCount = node.edgeLikedBy.count,
                        likeMediaPreviewCount = node.edgeMediaPreviewLike.count,
                        commentCount = node.edgeMediaToComment.count,
                        mediaPreview = node.mediaPreview,
                        typeName = node.typename, // example - "GraphImage"
                        thumbnailResources = node.thumbnailResources,
                        isVideo = node.isVideo
                    )
                )
            }
        }
        return UserMedia(
            pageInfo = pageInfo,
            userMediaInfoList = userMediaInfoList
        )
    }


    override suspend fun requestLikePrices(): AppResult<LikesPriceListModel> {
        return try {
            val response = api.getLikePriceList()
            if (response == null) {
                AppResult.Error(Exception("empty data requestLikePrices"))
            } else {
                Log.d("dwd", "requestLikePrices $response")
                handleSuccess(response)
            }
        } catch (e: Exception) {
            Log.d("dwd", "requestLikePrices " + e.message)
            AppResult.Error(e)
        }
    }

    override suspend fun requestFollowerPrices(): AppResult<LikesPriceListModel> {
        return try {
            val response = api.getFollowerPriceList()
            if (response == null) {
                AppResult.Error(Exception("empty data requestFollowerPrices"))
            } else {
                Log.d("dwd", "requestFollowerPrices $response")
                handleSuccess(response)
            }
        } catch (e: Exception) {
            Log.d("dwd", "requestFollowerPrices " + e.message)
            AppResult.Error(e)
        }
    }

    override suspend fun requestCommentPrices(): AppResult<LikesPriceListModel> {
        return try {
            val response = api.getCommentPriceList()
            if (response == null) {
                AppResult.Error(Exception("empty data requestCommentPrices"))
            } else {
                logD("requestCommentPrices $response")
                handleSuccess(response)
            }
        } catch (e: Exception) {
            logD("requestCommentPrices" + e.message)
            AppResult.Error(e)
        }
    }

}