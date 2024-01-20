package com.ins.boostyou.module

import com.ins.boostyou.billing.RemoteSettingsService
import com.ins.boostyou.billing.RemoteSettingsServiceImpl
import com.ins.boostyou.repository.InstMainRepo
import com.ins.boostyou.viewModel.MainActivityViewModel
import com.ins.boostyou.repoimpl.InstMainRepoImpl
import com.ins.boostyou.repoimpl.SignInUserRepoImpl
import com.ins.boostyou.repository.SignInUserRepo
import com.ins.boostyou.viewModel.SignInUserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val instModule = module {

    factory<InstMainRepo> {
        InstMainRepoImpl(get(), get())
    }

    factory<SignInUserRepo> {
        SignInUserRepoImpl(get(), get())
    }

    single<RemoteSettingsService> { RemoteSettingsServiceImpl(get()) }

    viewModel {
        MainActivityViewModel(get(), get())
    }

    viewModel {
        SignInUserViewModel(get(), get())
    }


}