package com.ins.boostyou.billing.data

data class InAppPurchaseResponse(
    val purchaseStatus: PurchaseStatus,
    val orderId: String,
    val subscriptionId: String,
    val token: String,
    val priceAmountMicros: Long,
    val currency: String
) {
    companion object {
        fun failedResponse() = InAppPurchaseResponse(
            PurchaseStatus.Failed,
            orderId = "",
            subscriptionId = "",
            token = "",
            0,
            ""
        )
    }
}

sealed interface PurchaseStatus {
    object Success : PurchaseStatus
    object Failed : PurchaseStatus
}
