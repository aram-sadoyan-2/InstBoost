package com.ins.boostyou.billing.data

data class InAppValidateRequestBody(
    val token: String,
    val packageId: String,
    val orderId: String,
    val priceAmountMicros: Long,
    val currency: String
) {
    companion object {
        const val TOKEN = "token"
        const val PACKAGE_ID = "subscription_id"
    }
}

fun InAppValidateRequestBody.toHashMap() = hashMapOf(
    InAppValidateRequestBody.TOKEN to token,
    InAppValidateRequestBody.PACKAGE_ID to packageId
)
