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

//    override suspend fun requestShortInstAccsToken(instAccessTokenRequestModel: InstAccessTokenRequestModel): AppResult<String> {
//        return try {
//            val response = api.requestShortInstAccsToken(
//                clientId = instAccessTokenRequestModel.client_id,
//                clientSecret = instAccessTokenRequestModel.client_secret,
//                redirectUri = instAccessTokenRequestModel.redirect_uri,
//                code = instAccessTokenRequestModel.code,
//                grantType = instAccessTokenRequestModel.grant_type
//            )
//            response.let {
//                Log.d("dwd", "Success InstMainRepoImpl requestShortInstAccsToken ---- $it")
//                if (it != null) {
//                    handleSuccess(it.accessToken)
//                } else {
//                    handleSuccess("error")
//                    AppResult.Error(Exception("empty token"))
//                }
//            }
//        } catch (e: Exception) {
//            Log.d("dwd", "Error " + e.message)
//            AppResult.Error(e)
//        }
//    }

//    override suspend fun requestLongLiveInstAccessToken(instAccessTokenRequestModel: InstAccessTokenRequestModel): AppResult<String> {
//        return try {
//            val response = api.requestLongLiveInstAccessToken(
//                grantType = "ig_exchange_token",
//                clientSecret = instAccessTokenRequestModel.client_secret,
//                shortLiveAccessToken = instAccessTokenRequestModel.shortLiveAccessToken
//            )
//            response.let {
//                if (it != null) {
//                    Log.d("dwd", "Success LongLiveAccessToken accessToken ${it.accessToken}")
//                    Log.d(
//                        "dwd",
//                        "Success LongLiveAccessToken expiresInSeconds  ${it.expiresInSeconds}"
//                    )
//                    saveTokenIntoLocal(context, it.accessToken)
//                    handleSuccess(it.accessToken)
//                } else {
//                    handleSuccess("LongLiveAccessToken error")
//                    AppResult.Error(Exception("empty token"))
//                }
//            }
//        } catch (e: Exception) {
//            Log.d("dwd", "LongLiveAccessToken Error " + e.message)
//            AppResult.Error(e)
//        }
//    }

//    override suspend fun requestInstUserBasicData(accessToken: String): AppResult<String> {
//        return try {
//            val response = api.requestInstUserBasicData(
//                fields = "id,username",
//                accessToken = accessToken
//            )
//            response.let {
//                if (it != null) {
//                    Log.d("dwd", "Username Succes ${it.userNam}")
//                    saveUsNmAndId(context, it.id, it.userNam)
//                    handleSuccess(it.userNam)
//                } else {
//                    AppResult.Error(Exception("empty user"))
//                }
//            }
//        } catch (e: Exception) {
//            Log.d("dwd", "Username Catch Error " + e.message)
//            AppResult.Error(e)
//        }
//    }

//    override suspend fun requestMedia(): AppResult<InstUserMediaJs> {
//        return try {
//            val response = api.requestMedia(
//                fields = "id,caption,media_type,media_url,permalink,thumbnail_url,username",
//                accessToken = getTokenFromLocal(context)
//            )
//            response.let {
//                if (it != null) {
//                    Log.d("dwd", "requestMedia Success ${it}")
//                    handleSuccess(it)
//                } else {
//                    handleSuccess("requestMedia error")
//                    AppResult.Error(Exception("empty token"))
//                }
//            }
//        } catch (e: Exception) {
//            Log.d("dwd", "requestMedia Catch Error " + e.message)
//            AppResult.Error(e)
//        }
//    }

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
            )?.let { response->
                val userData = parseToUserData(response)
                handleSuccess(userData)
            } ?:run {
                Log.d("dwd", "getPostData Error response is null")
                AppResult.Error(Exception("empty data"))
            }
        } catch (e: Exception) {
            Log.d("dwd", "getPostData Catch Error " + e.message)
            AppResult.Error(e)
        }
    }

    private fun parseToUserData(response: InstPrData?): UserData {
        val username = response?.data?.user?.username
        val fullName = response?.data?.user?.fullName
        val id = response?.data?.user?.id
        val profilePicUrl = response?.data?.user?.profilePicUrl
        val profilePicUrlHd = response?.data?.user?.profilePicUrlHd
        val followingCount = response?.data?.user?.edgeFollow?.count
        val followedByCount = response?.data?.user?.edgeFollowedBy?.count

        //contains User Media Info list
        val edgeOwnerToTimelineMedia = response?.data?.user?.edgeOwnerToTimelineMedia
        val userMedia = getUserMedia(edgeOwnerToTimelineMedia)

        Log.d("dwd", "lkhjwfwefwef")
        return UserData(fullName = fullName, userMedia = userMedia)
    }

    private fun getUserMedia(edgeOwnerToTimelineMedia: EdgeOwnerToTimelineMedia?): UserMedia {
        val pageInfo = edgeOwnerToTimelineMedia?.pageInfo
        val userMediaInfoList = mutableListOf<UserMediaInfoList>()
        edgeOwnerToTimelineMedia?.edges?.forEach { edge ->
            edge.node.let { node ->
                val id = node.id
                val displayUrl = node.displayUrl
                val thumbnailSrcUrl = node.thumbnailSrc
                val likeCount = node.edgeLikedBy
                val likeMediaPreviewCount = node.edgeMediaPreviewLike
                val commentCount = node.edgeMediaToComment
                val mediaPreview = node.mediaPreview
                val typeName = node.typename // example - "GraphImage"
                val thumbnailResources = node.thumbnailResources
                val isVideo = node.isVideo
                //contains video info
                //val videoUrl = node.videoUrl
                //....
                userMediaInfoList.add(UserMediaInfoList(id = id))
            }
        }
        return UserMedia(pageInfo = pageInfo, userMediaInfoList = userMediaInfoList)
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