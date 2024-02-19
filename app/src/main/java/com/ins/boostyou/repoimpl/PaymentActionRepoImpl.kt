package com.ins.boostyou.repoimpl

import android.util.Log
import com.ins.boostyou.api.RetrofitPostServiceApi
import com.ins.boostyou.model.request.BoostYouTaskRequest
import com.ins.boostyou.model.response.BaseResponse
import com.ins.boostyou.repository.PaymentActionRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PaymentActionRepoImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val apiService: RetrofitPostServiceApi
) : PaymentActionRepo {

    override fun requestTask(requestBody: BoostYouTaskRequest) = flow {
        try {
            val response = apiService.requestTask(
                taskType = requestBody.taskType,
                serviceUrl = requestBody.serviceUrl,
                quality = requestBody.quality,
                count = requestBody.count,
                userName = requestBody.userName,
                comments = requestBody.comments,
            )
            response?.let {
                when (it.status) {
                    "success" -> {
                        emit(
                            it
                        )
                    }
                    else -> emit(it)
                }
            } ?: run {
                emit(BaseResponse(message = "response is null"))
            }
        } catch (e: Exception) {
            Log.d("dwd", "requestTask " + e.message)
            emit(BaseResponse(message = e.message))
        }
    }.flowOn(ioDispatcher)

}
