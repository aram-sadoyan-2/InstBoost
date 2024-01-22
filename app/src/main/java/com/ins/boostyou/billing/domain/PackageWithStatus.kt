package com.ins.boostyou.billing.domain

interface PackageWithStatus {
    val processStatus: PackageProcessStatus
}

enum class PackageProcessStatus {
    PROCESSED,
    UN_PROCESSED
}
