package com.ins.boostyou.billing.domain


sealed interface InAppPurchaseState {
    object Initial : InAppPurchaseState
    data class Success(
        val token: String,
        val subscriptionId: String,
        val orderId: String,
        val packageInfo: PackageDetails = PackageDetails.default()
    ) : InAppPurchaseState

    object Failure : InAppPurchaseState
    object UserCanceled : InAppPurchaseState
}
