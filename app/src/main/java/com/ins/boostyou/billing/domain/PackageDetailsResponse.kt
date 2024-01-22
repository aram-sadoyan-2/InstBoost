package com.ins.boostyou.billing.domain

import com.android.billingclient.api.ProductDetails

data class PackageDetailsResponse(
    val packageDetailsStatus: PackageDetailsStatus = PackageDetailsStatus.Error,
    val productDetails: List<ProductDetails> = emptyList()
)

sealed interface PackageDetailsStatus {
    object Ok : PackageDetailsStatus
    object Error : PackageDetailsStatus
}
