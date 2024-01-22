package com.ins.boostyou.repoimpl

import android.util.Log
import com.ins.boostyou.api.RetrofitPostServiceApi
import com.ins.boostyou.billing.data.InAppPurchaseResponse
import com.ins.boostyou.billing.data.InAppValidateRequestBody
import com.ins.boostyou.billing.data.PurchaseStatus
import com.ins.boostyou.billing.data.toHashMap
import com.ins.boostyou.repository.InAppPaymentValidationRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class InAppPaymentValidationRepoImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val apiService: RetrofitPostServiceApi
) : InAppPaymentValidationRepo {

    override fun validateInAppPayment(requestBody: InAppValidateRequestBody) = flow {
        try {
            val response = apiService.validate(requestBody.toHashMap())
            response?.let {
                when (it.status) {
                    "success" -> {
                       emit(InAppPurchaseResponse(
                           PurchaseStatus.Success,
                           requestBody.orderId,
                           requestBody.packageId,
                           requestBody.token,
                           requestBody.priceAmountMicros,
                           requestBody.currency
                       ))
                    }
                    else -> emit(InAppPurchaseResponse.failedResponse())
                }
            } ?: run {
                emit(InAppPurchaseResponse.failedResponse())
            }
        } catch (e: Exception) {
            Log.d("dwd", "validate " + e.message)
            emit(InAppPurchaseResponse.failedResponse())
        }
    }.flowOn(ioDispatcher)
}
