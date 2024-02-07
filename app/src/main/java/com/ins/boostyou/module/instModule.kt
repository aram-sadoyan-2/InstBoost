package com.ins.boostyou.module

import com.ins.boostyou.billing.GoogleBillingServiceImpl
import com.ins.boostyou.billing.InstBoostPaymentService
import com.ins.boostyou.billing.RemoteSettingsService
import com.ins.boostyou.billing.RemoteSettingsServiceImpl
import com.ins.boostyou.repoimpl.InAppPaymentValidationRepoImpl
import com.ins.boostyou.repository.InstMainRepo
import com.ins.boostyou.viewModel.MainActivityViewModel
import com.ins.boostyou.repoimpl.InstMainRepoImpl
import com.ins.boostyou.repoimpl.SignInUserRepoImpl
import com.ins.boostyou.repository.InAppPaymentValidationRepo
import com.ins.boostyou.repository.SignInUserRepo
import com.ins.boostyou.viewModel.ComposeNavigationViewModel
import com.ins.boostyou.viewModel.InAppPurchaseViewModel
import com.ins.boostyou.viewModel.SignInUserViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val instModule = module {
    factory<InAppPaymentValidationRepo> {
        InAppPaymentValidationRepoImpl(ioDispatcher = Dispatchers.IO, get())
    }
    factory<InstBoostPaymentService> {
        GoogleBillingServiceImpl(get(), get(), get())
    }
    factory<InstMainRepo> {
        InstMainRepoImpl(get(), get())
    }

    factory<SignInUserRepo> {
        SignInUserRepoImpl(get(), get())
    }

    factory<RemoteSettingsService> { RemoteSettingsServiceImpl(get()) }


    viewModel {
        MainActivityViewModel(get(), get(), get())
    }

    viewModel {
        SignInUserViewModel(get(), get())
    }

    viewModel {
        InAppPurchaseViewModel(instBoostPaymentService = get(), get(), get())
    }

    viewModel {
        ComposeNavigationViewModel(get())
    }


}