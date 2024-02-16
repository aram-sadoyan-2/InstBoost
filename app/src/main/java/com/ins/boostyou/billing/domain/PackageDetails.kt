package com.ins.boostyou.billing.domain

data class PackageDetails(
    val packageId: String,
    override val processStatus: PackageProcessStatus = PackageProcessStatus.UN_PROCESSED,
    val period: String = "P1M",
    val introductoryPrice: String = "",
    val price: String = "",
    val currencyCode: String = "USD",
    val priceMicros: Long = 0L,
    val name: String = "",
    val basePlanTags: List<String> = emptyList()
) : PackageWithStatus {
    companion object {
        fun default() = PackageDetails("")
    }
}
