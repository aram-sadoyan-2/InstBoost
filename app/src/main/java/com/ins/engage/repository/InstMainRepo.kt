package com.ins.engage.repository

import com.ins.engage.AppResult
import com.ins.engage.model.request.InstAccessTokenRequestModel
import com.ins.engage.model.response.InstPrData
import com.ins.engage.model.response.InstUserMediaJs
import com.ins.engage.model.response.InstaProfileModel

interface InstMainRepo {
    suspend fun requestShortInstAccsToken(instAccessTokenRequestModel: InstAccessTokenRequestModel): AppResult<String>
    suspend fun requestLongLiveInstAccessToken(instAccessTokenRequestModel: InstAccessTokenRequestModel): AppResult<String>
    suspend fun requestInstUserBasicData(accessToken: String): AppResult<String>
    suspend fun requestMedia(): AppResult<InstUserMediaJs>
    suspend fun getPostData(): AppResult<InstaProfileModel>
    suspend fun getPostDataFromNewJson(): AppResult<InstPrData>
}