package com.ins.boostyou.viewModel

import com.ins.boostyou.model.request.BoostYouTaskRequest
import com.ins.boostyou.model.response.BaseResponse
import com.ins.boostyou.module.ProfDispatchers
import com.ins.boostyou.repository.PaymentActionRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PaymentActionsViewModel(
    private var repo: PaymentActionRepo,
    dispatchers: ProfDispatchers
) : BaseViewModel(dispatchers) {




}