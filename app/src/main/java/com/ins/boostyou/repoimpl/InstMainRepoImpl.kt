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
import com.ins.boostyou.model.response.InstUserMediaJs
import com.ins.boostyou.model.response.boostyou.RemotePackages
import com.ins.boostyou.repository.InstMainRepo
import com.ins.engage.model.response.InstPrData

class InstMainRepoImpl(
    private val api: RetrofitPostServiceApi,
    private val context: Context
) : InstMainRepo {

    override suspend fun requestShortInstAccsToken(instAccessTokenRequestModel: InstAccessTokenRequestModel): AppResult<String> {
        return try {
            val response = api.requestShortInstAccsToken(
                clientId = instAccessTokenRequestModel.client_id,
                clientSecret = instAccessTokenRequestModel.client_secret,
                redirectUri = instAccessTokenRequestModel.redirect_uri,
                code = instAccessTokenRequestModel.code,
                grantType = instAccessTokenRequestModel.grant_type
            )
            response.let {
                Log.d("dwd", "Success InstMainRepoImpl requestShortInstAccsToken ---- $it")
                if (it != null) {
                    handleSuccess(it.accessToken)
                } else {
                    handleSuccess("error")
                    AppResult.Error(Exception("empty token"))
                }
            }
        } catch (e: Exception) {
            Log.d("dwd", "Error " + e.message)
            AppResult.Error(e)
        }
    }

    override suspend fun requestLongLiveInstAccessToken(instAccessTokenRequestModel: InstAccessTokenRequestModel): AppResult<String> {
        return try {
            val response = api.requestLongLiveInstAccessToken(
                grantType = "ig_exchange_token",
                clientSecret = instAccessTokenRequestModel.client_secret,
                shortLiveAccessToken = instAccessTokenRequestModel.shortLiveAccessToken
            )
            response.let {
                if (it != null) {
                    Log.d("dwd", "Success LongLiveAccessToken accessToken ${it.accessToken}")
                    Log.d(
                        "dwd",
                        "Success LongLiveAccessToken expiresInSeconds  ${it.expiresInSeconds}"
                    )
                    saveTokenIntoLocal(context, it.accessToken)
                    handleSuccess(it.accessToken)
                } else {
                    handleSuccess("LongLiveAccessToken error")
                    AppResult.Error(Exception("empty token"))
                }
            }
        } catch (e: Exception) {
            Log.d("dwd", "LongLiveAccessToken Error " + e.message)
            AppResult.Error(e)
        }
    }

    override suspend fun requestInstUserBasicData(accessToken: String): AppResult<String> {
        return try {
            val response = api.requestInstUserBasicData(
                fields = "id,username",
                accessToken = accessToken
            )
            response.let {
                if (it != null) {
                    Log.d("dwd", "Username Succes ${it.userNam}")
                    saveUsNmAndId(context, it.id, it.userNam)
                    handleSuccess(it.userNam)
                } else {
                    AppResult.Error(Exception("empty user"))
                }
            }
        } catch (e: Exception) {
            Log.d("dwd", "Username Catch Error " + e.message)
            AppResult.Error(e)
        }
    }

    override suspend fun requestMedia(): AppResult<InstUserMediaJs> {
        return try {
            val response = api.requestMedia(
                fields = "id,caption,media_type,media_url,permalink,thumbnail_url,username",
                accessToken = getTokenFromLocal(context)
            )
            response.let {
                if (it != null) {
                    Log.d("dwd", "requestMedia Success ${it}")
                    handleSuccess(it)
                } else {
                    handleSuccess("requestMedia error")
                    AppResult.Error(Exception("empty token"))
                }
            }
        } catch (e: Exception) {
            Log.d("dwd", "requestMedia Catch Error " + e.message)
            AppResult.Error(e)
        }
    }

    override suspend fun getPostDataFromNewJson(): AppResult<InstPrData> {
        return try {
            //TODO NEW INST DATA REQUEST
//            val response2 = api.getPostDataFromNewJson(
//                userName = "aramsadoy",
//                secFetchDest = "empty",
//                secFetchMode = "cors",
//                secFetchSite = "same-origin",
//                appId = 936619743392459,
//                claim = 0,
//                requestedWith = "XMLHttpRequest"
//            )

            val response = InstPrData()

            if (response == null) {
                Log.d("dwd", "getPostData Success $response")
                AppResult.Error(Exception("empty data"))
            } else {
                handleSuccess(response)
            }
        } catch (e: Exception) {
            Log.d("dwd", "getPostData Catch Error " + e.message)
            AppResult.Error(e)
        }
    }




    override suspend fun requestRemotePackages(): AppResult<List<RemotePackages>> {
        return try {
            val response = api.requestRemotePackages()
            if (response == null) {
                Log.d("dwd", "getPostData Success $response")
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