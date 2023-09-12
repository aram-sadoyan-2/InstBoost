package com.ins.engage.repository

import com.ins.engage.AppResult
import com.ins.engage.model.request.InstAccessTokenRequestModel
import com.ins.engage.model.response.BaseResponse

interface InstMainRepo {
    suspend fun requestGoogleLogin(instAccessTokenRequestModel: InstAccessTokenRequestModel): AppResult<BaseResponse>
}