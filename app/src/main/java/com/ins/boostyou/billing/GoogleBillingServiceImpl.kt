package com.ins.boostyou.billing

import android.content.Context
import android.util.Log
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.queryProductDetails
import com.ins.boostyou.model.response.boostyou.RemotePackages
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class GoogleBillingServiceImpl(
    private val context: Context,
    private val remoteSettings: RemoteSettingsService,
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
        Log.d("dwd", "GoogleBillingServiceImpl onBillingSetupFinished $billingResult.responseCode")
        coroutineScope.launch {
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                // The BillingClient is ready. You can query purchases here.
                getProductsInfo(remoteSettings.getRemoteSettings())
                //todo if packageIds exists
                //todo query qurchase
                //todo else call again to back-end
                Log.d("dwd", "GoogleBillingServiceImpl onBillingSetupFinished ")
            }
        }
    }

    private suspend fun getProductsInfo(listOfProductIds: List<RemotePackages>?) {

    }

}