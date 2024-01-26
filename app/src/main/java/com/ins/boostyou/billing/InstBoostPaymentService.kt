package com.ins.boostyou.billing

import android.app.Activity
import com.ins.boostyou.billing.domain.InAppPurchaseState
import com.ins.boostyou.billing.domain.PackageDetails
import kotlinx.coroutines.flow.Flow

interface InstBoostPaymentService {
    fun initialize()

    fun launchInAppBillingFlow(activity: Activity, sku: String): Flow<InAppPurchaseState>

    suspend fun getPackageDetail(packageId: String): PackageDetails

    suspend fun consumePackage(token: String)

}