package com.ins.engage.billing

import android.content.Context
import android.util.Log
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class GoogleBillingServiceImpl(
    private val context: Context,
    private val defaultScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default),
) : PurchasesUpdatedListener, BillingClientStateListener, InstBoostPaymentService {

    private var billingClient: BillingClient? = null


    override fun initialize() {
        billingClient =
            BillingClient.newBuilder(context).setListener(this).enablePendingPurchases().build()
        billingClient?.startConnection(this)
    }

    override fun onPurchasesUpdated(
        billingResult: BillingResult,
        purchaseList: MutableList<Purchase>?
    ) {
        Log.d("dwd", "GoogleBillingServiceImpl onPurchasesUpdated")
    }

    override fun onBillingServiceDisconnected() {
        Log.d("dwd", "GoogleBillingServiceImpl onBillingServiceDisconnected")
    }

    override fun onBillingSetupFinished(billingResult: BillingResult) {
        Log.d("dwd", "GoogleBillingServiceImpl onBillingSetupFinished " + billingResult.responseCode)
    }

}