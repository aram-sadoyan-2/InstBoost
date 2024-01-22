package com.ins.boostyou.billing

import android.app.Activity
import android.content.Context
import android.util.Log
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.queryProductDetails
import com.ins.boostyou.billing.domain.InAppPurchaseState
import com.ins.boostyou.billing.domain.PackageDetails
import com.ins.boostyou.billing.domain.PackageDetailsResponse
import com.ins.boostyou.billing.domain.PackageDetailsStatus
import com.ins.boostyou.billing.domain.PackageProcessStatus
import com.ins.boostyou.model.response.boostyou.RemotePackages
import com.ins.boostyou.utils.orFalse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

const val FAKE_ID = "fake_id"
const val PERIOD_LIFETIME_SUFFIX = "L"
class GoogleBillingServiceImpl(
    private val context: Context,
    private val remoteSettings: RemoteSettingsService,
    //private val mapper: GooglePaymentInfoMapper
) : PurchasesUpdatedListener, BillingClientStateListener, InstBoostPaymentService {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private var billingClient: BillingClient? = null
    private var inAppDetailsResponse = PackageDetailsResponse()
    private val inAppPurchaseDoneFlow =
        MutableStateFlow<InAppPurchaseState>(InAppPurchaseState.Initial)
    private var remotePackages: MutableList<RemotePackages>? = null


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
                remotePackages = remoteSettings.getRemoteSettings()?.toMutableList()
                refreshDetails()
               // getProductsInfo(remoteSettings.getRemoteSettings())
                //todo if packageIds exists
                //todo query qurchase
                //todo else call again to back-end
                Log.d("dwd", "GoogleBillingServiceImpl onBillingSetupFinished ")
            }
        }
    }

    override fun launchInAppBillingFlow(
        activity: Activity,
        sku: String
    ): Flow<InAppPurchaseState> {
        inAppPurchaseDoneFlow.value = InAppPurchaseState.Initial
        val productDetails = inAppDetailsResponse.productDetails.find { it.productId == sku }
        if (productDetails == null || !billingClient?.isReady.orFalse()) {
            inAppPurchaseDoneFlow.value = InAppPurchaseState.Failure
            return inAppPurchaseDoneFlow
        }
        //inAppPendingItems.add(productDetails.productId)
        val flowParams = BillingFlowParams.newBuilder().setProductDetailsParamsList(
            listOf(
                BillingFlowParams.ProductDetailsParams.newBuilder()
                    .setProductDetails(productDetails).build()
            )
        )
        billingClient?.launchBillingFlow(activity, flowParams.build())
        return inAppPurchaseDoneFlow
    }

    override suspend fun getPackageDetail(packageId: String): PackageDetails {
        refreshDetails()
        return mapProductDetails(inAppDetailsResponse).get(packageId) ?: PackageDetails.default()
    }

    private suspend fun refreshDetails(){
        inAppDetailsResponse = fetchProductDetails()
    }
    private suspend fun fetchProductDetails(): PackageDetailsResponse {////todo oooooo
        val productDetailsResult = billingClient?.queryProductDetails(initProductDetailsParams())
        return when (productDetailsResult?.billingResult?.responseCode) {
            BillingClient.BillingResponseCode.OK -> PackageDetailsResponse(
                PackageDetailsStatus.Ok,
                productDetailsResult.productDetailsList?.filter { it.productType == BillingClient.ProductType.INAPP }
                    .orEmpty()
            )

            else -> PackageDetailsResponse(PackageDetailsStatus.Error)
        }
    }

    private fun initProductDetailsParams(): QueryProductDetailsParams {
        val productList = remotePackages?.map {
            QueryProductDetailsParams.Product.newBuilder().setProductId(it.packageId.orEmpty())
                .setProductType(BillingClient.ProductType.INAPP).build()
        }
        return QueryProductDetailsParams.newBuilder().setProductList(productList.orEmpty())
            .build()
    }


    fun mapProductDetails(
        packageDetailsResponse: PackageDetailsResponse
    ): Map<String, PackageDetails> = when {
        packageDetailsResponse.packageDetailsStatus == PackageDetailsStatus.Error && packageDetailsResponse.productDetails.isEmpty() -> {
            mapOf(FAKE_ID to PackageDetails(packageId = FAKE_ID, processStatus = PackageProcessStatus.UN_PROCESSED))
        }
        packageDetailsResponse.packageDetailsStatus == PackageDetailsStatus.Ok && packageDetailsResponse.productDetails.isEmpty() -> {
            mapOf(FAKE_ID to PackageDetails(packageId = FAKE_ID, processStatus = PackageProcessStatus.PROCESSED))
        }
        else -> parseSuccessState(packageDetailsResponse)
    }

    private fun parseSuccessState(
        packageDetailsResponse: PackageDetailsResponse
    ): Map<String, PackageDetails> =
        packageDetailsResponse.productDetails.map {
            PackageDetails(
                packageId = it.productId,
                processStatus = PackageProcessStatus.PROCESSED,
                price = getInAppPackagePricingPhase(it)?.formattedPrice.orEmpty(),
                currencyCode = getInAppPackagePricingPhase(it)?.priceCurrencyCode.orEmpty(),
                priceMicros = getInAppPackagePricingPhase(it)?.priceAmountMicros ?: 0L,
                period = PERIOD_LIFETIME_SUFFIX,
                basePlanTags = it.subscriptionOfferDetails?.lastOrNull()?.offerTags.orEmpty(),
            )
        }.associateBy { it.packageId }

    private fun getInAppPackagePricingPhase(productDetails: ProductDetails): ProductDetails.OneTimePurchaseOfferDetails? =
        productDetails.oneTimePurchaseOfferDetails

}