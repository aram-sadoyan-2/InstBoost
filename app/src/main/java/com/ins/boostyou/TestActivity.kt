package com.ins.boostyou

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.queryProductDetails
import com.ins.boostyou.composable.FistPage
import com.ins.boostyou.databinding.ActivityLoginBinding
import com.ins.boostyou.viewModel.MainActivityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestActivity: ComponentActivity() {
    private lateinit var bindingView: ActivityLoginBinding
    private val mainActivityViewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPackages()
        bindingView = ActivityLoginBinding.inflate(layoutInflater)
        setContent {
            FistPage()
        }
        val purchasesUpdatedListener =
            PurchasesUpdatedListener { billingResult, purchases ->
                // To be implemented in a later section.
            }

        val billingClient = BillingClient.newBuilder(this)
            .setListener(purchasesUpdatedListener)
            .enablePendingPurchases()
            .build()
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode ==  BillingClient.BillingResponseCode.OK) {
                    // The BillingClient is ready. You can query purchases here.
                }
            }
            override fun onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        })

        bindingView.btn?.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val productList = mutableListOf<QueryProductDetailsParams.Product>()
                productList.add(QueryProductDetailsParams.Product.newBuilder()
                    .setProductId("coin_20")
                    .setProductType(BillingClient.ProductType.INAPP)
                    .build()
                )
                val params = QueryProductDetailsParams.newBuilder()
                params.setProductList(productList.toList())

                // leverage queryProductDetails Kotlin extension function
                billingClient.queryProductDetails(params.build()).let(::println)

            }
        }
    }

    private fun requestPackages() {
        //mainActivityViewModel.requestRemotePackages()
        mainActivityViewModel.requestRemotePackages.observe(this) {
            it?.let {
                when (it) {
                    is AppResult.Success -> {
                        Log.d("dwd", "requestInstUserdata ---- " + it.successData)
                    }

                    is AppResult.Error -> {
                        Log.d("dwd", "requestInstUserdata Error")
                    }
                }
            }
        }
    }
}

