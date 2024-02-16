package com.ins.boostyou.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.ins.boostyou.AppResult
import com.ins.boostyou.billing.InstBoostPaymentService
import com.ins.boostyou.billing.domain.InAppPurchaseState
import com.ins.boostyou.databinding.ActivityMainBinding
import com.ins.boostyou.viewModel.InAppPurchaseViewModel
import com.ins.boostyou.viewModel.MainActivityViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private lateinit var bindingView: ActivityMainBinding
    private val mainActivityViewModel: MainActivityViewModel by viewModel()
    private val inAppPurchaseViewModel: InAppPurchaseViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       inAppPurchaseViewModel.initialize() //todo move
        bindingView = ActivityMainBinding.inflate(layoutInflater)
        val view = bindingView.root
        setContentView(view)
        observeForInAppPurchaseState()
        observeForInAppPurchase()
        bindingView.btnLogOut.setOnClickListener {
            inAppPurchaseViewModel.launchInAppBillingFlow(this, "coin_20")
        }
    }

    private fun observeForInAppPurchaseState() {
        inAppPurchaseViewModel.inAppPurchaseStateLiveData.observe(this) {
                inAppPurchaseState: InAppPurchaseState? ->
            when (inAppPurchaseState) {
                is InAppPurchaseState.Success -> {
                    Log.d("dwd","InAppPurchaseStateLiveData Success" )
//                    if (!isFinishing) {
//                        launchOnUi { showHideDialog(true) }
//                    }
                    inAppPurchaseState.run {
//                        shopSubscribeViewModel.validateInAppPurchase(
//                            InAppValidateRequestBody(
//                                token,
//                                subscriptionId,
//                                orderId,
//                                packageInfo.priceMicros,
//                                packageInfo.currencyCode))
                    }
                }
                is InAppPurchaseState.Failure -> {
                    Log.d("dwd","InAppPurchaseStateLiveData Failure" )

                    val oneTimePaymentIntent = Intent()
//                    val paymentInfo =
//                        PaymentInfo(Status.FAILURE, "sku", "", "", userStateManager.userId)
                   // oneTimePaymentIntent.putExtra(EXTRA_PAYMENT_INFO, paymentInfo)
                   // setResult(ONE_TIME_SUBSCRIPTION_REQUEST_CODE, oneTimePaymentIntent)
                   // finish()
                }
                is InAppPurchaseState.UserCanceled -> {
                    Log.d("dwd","InAppPurchaseStateLiveData UserCanceled" )
                    //setResult(RESULT_CANCELED)
                    //finish()
                }
                else -> {}
            }
        }
    }

    private fun observeForInAppPurchase() {
        inAppPurchaseViewModel.inAppPurchaseLiveData.observe(this) { purchaseResponse ->
            if (isFinishing) return@observe
            Log.d("dwd","PurchaseStatus ${purchaseResponse.purchaseStatus}")

        }
    }
}