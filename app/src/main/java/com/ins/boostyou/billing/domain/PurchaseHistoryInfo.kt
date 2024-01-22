package com.ins.boostyou.billing.domain

data class PurchaseHistoryInfo(
    val packageId: String,
    val purchaseToken: String = "",
    override val processStatus: PackageProcessStatus = PackageProcessStatus.UN_PROCESSED,
) : PackageWithStatus {
    companion object {
        fun default() = PurchaseHistoryInfo("")
    }
}
