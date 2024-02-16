package com.ins.boostyou.repository

import com.ins.boostyou.model.request.BoostYouTaskRequest
import com.ins.boostyou.model.response.BaseResponse
import kotlinx.coroutines.flow.Flow

interface PaymentActionRepo {
    fun requestTask(requestBody: BoostYouTaskRequest): Flow<BaseResponse>
}
