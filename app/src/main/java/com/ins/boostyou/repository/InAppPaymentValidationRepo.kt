package com.ins.boostyou.repository

import com.ins.boostyou.billing.data.InAppPurchaseResponse
import com.ins.boostyou.billing.data.InAppValidateRequestBody
import kotlinx.coroutines.flow.Flow

interface InAppPaymentValidationRepo {
    fun validateInAppPayment(requestBody: InAppValidateRequestBody): Flow<InAppPurchaseResponse>
}
