package com.ins.boostyou.billing

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
import kotlinx.coroutines.launch

class GoogleBillingServiceImpl(
    private val context: Context,
) : PurchasesUpdatedListener, BillingClientStateListener, InstBoostPaymentService {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
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
        coroutineScope.launch {
            if (billingResult.responseCode ==  BillingClient.BillingResponseCode.OK) {
                // The BillingClient is ready. You can query purchases here.
                Log.d("dwd", "GoogleBillingServiceImpl onBillingSetupFinished ")

            }
        }

    }

}