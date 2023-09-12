package com.ins.engage.repoimpl

import com.ins.engage.AppResult
import com.ins.engage.api.RetrofitPostService
import com.ins.engage.handleSuccess
import com.ins.engage.model.request.InstAccessTokenRequestModel
import com.ins.engage.model.response.BaseResponse
import com.ins.engage.repository.InstMainRepo

class InstMainRepoImpl(private val api: RetrofitPostService) : InstMainRepo {

    override suspend fun requestGoogleLogin(instAccessTokenRequestModel: InstAccessTokenRequestModel): AppResult<BaseResponse> {
        return try {
            val response = api.requestInstAccessToken(instAccessTokenRequestModel)
            response.let {
                if (it.accessToken != null) {
                    //saveToken(it.accessToken)
                    handleSuccess(it)
                } else {
                    handleSuccess(it)
                    //  AppResult.Error(Exception("empty token"))
                }
            }
        } catch (e: Exception) {
            AppResult.Error(e)
        }
    }
}