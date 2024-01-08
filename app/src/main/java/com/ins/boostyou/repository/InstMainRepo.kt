package com.ins.boostyou.repository

import com.ins.boostyou.AppResult
import com.ins.boostyou.model.request.InstAccessTokenRequestModel
import com.ins.boostyou.model.response.InstUserMediaJs
import com.ins.engage.model.response.InstPrData

interface InstMainRepo {
    suspend fun requestShortInstAccsToken(instAccessTokenRequestModel: InstAccessTokenRequestModel): AppResult<String>
    suspend fun requestLongLiveInstAccessToken(instAccessTokenRequestModel: InstAccessTokenRequestModel): AppResult<String>
    suspend fun requestInstUserBasicData(accessToken: String): AppResult<String>
    suspend fun requestMedia(): AppResult<InstUserMediaJs>
    suspend fun getPostDataFromNewJson(): AppResult<InstPrData>
}