package com.ins.boostyou.repoimpl

import android.content.Context
import android.util.Log
import com.ins.boostyou.AppResult
import com.ins.boostyou.api.RetrofitPostServiceApi
import com.ins.boostyou.controller.FileDataUtils.Companion.getTokenFromLocal
import com.ins.boostyou.controller.FileDataUtils.Companion.saveTokenIntoLocal
import com.ins.boostyou.controller.FileDataUtils.Companion.saveUsNmAndId
import com.ins.boostyou.handleSuccess
import com.ins.boostyou.model.request.InstAccessTokenRequestModel
import com.ins.boostyou.model.response.EdgeOwnerToTimelineMedia
import com.ins.boostyou.model.response.InstUserMediaJs
import com.ins.boostyou.model.response.boostyou.RemotePackages
import com.ins.boostyou.repository.InstMainRepo
import com.ins.boostyou.model.response.InstPrData
import com.ins.boostyou.model.response.UserData
import com.ins.boostyou.model.response.UserMedia
import com.ins.boostyou.model.response.UserMediaInfoList

class InstMainRepoImpl(
    private val api: RetrofitPostServiceApi,
    private val context: Context
) : InstMainRepo {

    override suspend fun getPostDataFromNewJson(): AppResult<UserData> {
        return try {
            api.getPostDataFromNewJson(
                userName = "aramsadoy",
                secFetchDest = "empty",
                secFetchMode = "cors",
                secFetchSite = "same-origin",
                appId = 936619743392459,
                claim = 0,
                requestedWith = "XMLHttpRequest"
            )?.let { response ->
                val userData = parseToUserData(response)
                handleSuccess(userData)
            } ?: run {
                Log.d("dwd", "getPostData Error response is null")
                AppResult.Error(Exception("empty data"))
            }
        } catch (e: Exception) {
            Log.d("dwd", "getPostData Catch Error " + e.message)
            AppResult.Error(e)
        }
    }

    private fun parseToUserData(response: InstPrData?): UserData {
        //contains User Media Info list
        val edgeOwnerToTimelineMedia = response?.data?.user?.edgeOwnerToTimelineMedia
        val userMedia = getUserMedia(edgeOwnerToTimelineMedia)
        return UserData(
            id = response?.data?.user?.id,
            userName = response?.data?.user?.username,
            fullName = response?.data?.user?.fullName,
            profilePicUrl = response?.data?.user?.profilePicUrl,
            profilePicUrlHd = response?.data?.user?.profilePicUrlHd,
            followingCount = response?.data?.user?.edgeFollow?.count,
            followedByCount = response?.data?.user?.edgeFollowedBy?.count,
            userMedia = userMedia
        )
    }

    private fun getUserMedia(edgeOwnerToTimelineMedia: EdgeOwnerToTimelineMedia?): UserMedia {
        val pageInfo = edgeOwnerToTimelineMedia?.pageInfo
        val userMediaInfoList = mutableListOf<UserMediaInfoList>()
        edgeOwnerToTimelineMedia?.edges?.forEach { edge ->
            edge.node.let { node ->
                //contains video info
                //val videoUrl = node.videoUrl
                //....
                userMediaInfoList.add(UserMediaInfoList(
                    id = node.id,
                    displayUrl = node.displayUrl,
                    thumbnailSrcUrl = node.thumbnailSrc,
                    likeCount = node.edgeLikedBy.count,
                    likeMediaPreviewCount = node.edgeMediaPreviewLike.count,
                    commentCount = node.edgeMediaToComment.count,
                    mediaPreview = node.mediaPreview,
                    typeName = node.typename, // example - "GraphImage"
                    thumbnailResources = node.thumbnailResources,
                    isVideo = node.isVideo
                ))
            }
        }
        return UserMedia(
            pageInfo = pageInfo,
            userMediaInfoList = userMediaInfoList)
    }

    override suspend fun requestRemotePackages(): AppResult<List<RemotePackages>> {
        return try {
            val response = api.requestRemotePackages()
            if (response == null) {
                AppResult.Error(Exception("empty data"))
            } else {
                handleSuccess(response)
            }
        } catch (e: Exception) {
            Log.d("dwd", "getPostData Catch Error " + e.message)
            AppResult.Error(e)
        }
    }
}