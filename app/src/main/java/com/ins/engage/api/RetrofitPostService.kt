package com.ins.engage.api

import com.ins.engage.model.request.InstAccessTokenRequestModel
import com.ins.engage.model.response.BaseResponse
import retrofit2.http.*

interface RetrofitPostService {

    @POST("access_token")
    suspend fun requestInstAccessToken(
        @Body instAccessTokenRequestModel: InstAccessTokenRequestModel,
    ): BaseResponse

}