package com.ins.engage.module

import com.ins.engage.repoimpl.InstMainRepoImpl
import com.ins.engage.repository.InstMainRepo
import com.ins.engage.viewModel.InstMainViewModel
import com.ins.engage.viewModel.MainActivityViewModel
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