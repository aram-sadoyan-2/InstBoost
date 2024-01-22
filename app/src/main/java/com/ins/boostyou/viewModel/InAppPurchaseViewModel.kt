package com.ins.boostyou.viewModel

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ins.boostyou.billing.InstBoostPaymentService
import com.ins.boostyou.billing.data.InAppPurchaseResponse
import com.ins.boostyou.billing.data.InAppValidateRequestBody
import com.ins.boostyou.billing.data.PurchaseStatus
import com.ins.boostyou.billing.domain.InAppPurchaseState
import com.ins.boostyou.module.ProfDispatchers
import com.ins.boostyou.repository.InAppPaymentValidationRepo
import kotlinx.coroutines.flow.distinctUntilChanged

class InAppPurchaseViewModel(
    private var instBoostPaymentService: InstBoostPaymentService,
    private var inAppPaymentValidationRepo: InAppPaymentValidationRepo,
    dispatchers: ProfDispatchers
) : BaseViewModel(dispatchers) {

    private val _inAppPurchaseStateLiveData = MutableLiveData<InAppPurchaseState>()
    val inAppPurchaseStateLiveData: LiveData<InAppPurchaseState> = _inAppPurchaseStateLiveData

    private val _inAppPurchaseLiveData = MutableLiveData<InAppPurchaseResponse>()
    val inAppPurchaseLiveData: LiveData<InAppPurchaseResponse> = _inAppPurchaseLiveData
    fun initialize(){
        instBoostPaymentService.initialize()
    }
    fun launchInAppBillingFlow(activity: Activity, sku: String) = launchOnUI {
        val packageInfo = instBoostPaymentService.getPackageDetail(sku)
        instBoostPaymentService.launchInAppBillingFlow(activity, sku).distinctUntilChanged().collect {
                purchaseState ->
            _inAppPurchaseStateLiveData.value =
                when (purchaseState) {
                    is InAppPurchaseState.Success -> purchaseState.copy(packageInfo = packageInfo)
                    else -> purchaseState
                }
        }
    }

    fun validateInAppPurchase(requestBody: InAppValidateRequestBody) = launchOnUI {
        inAppPaymentValidationRepo.validateInAppPayment(requestBody).collect {
            if (it.purchaseStatus == PurchaseStatus.Success) {
              //  oneTimePaymentValidationUseCase.consumePackage(it.token) //todo
            }
            _inAppPurchaseLiveData.value =
                InAppPurchaseResponse(
                    it.purchaseStatus,
                    requestBody.orderId,
                    requestBody.packageId,
                    requestBody.token,
                    requestBody.priceAmountMicros,
                    requestBody.currency)
        }
    }


}