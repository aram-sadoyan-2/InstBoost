package com.ins.boostyou.repository

import com.ins.boostyou.model.request.BoostYouTaskRequest
import com.ins.boostyou.model.response.BaseResponse
import com.ins.boostyou.model.response.boostyou.UserInfo
import kotlinx.coroutines.flow.Flow

interface PaymentActionRepo {
    fun requestTask(requestBody: BoostYouTaskRequest, userInfo: UserInfo): Flow<BaseResponse>
}
