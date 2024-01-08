package com.ins.boostyou.module

import com.ins.boostyou.repository.InstMainRepo
import com.ins.boostyou.viewModel.InstMainViewModel
import com.ins.boostyou.viewModel.MainActivityViewModel
import com.ins.boostyou.repoimpl.InstMainRepoImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val instModule = module {

    factory<InstMainRepo> {
        InstMainRepoImpl(get(), get())
    }

    viewModel {
        InstMainViewModel(get(), get())
    }

    viewModel {
        MainActivityViewModel(get(), get())
    }


}