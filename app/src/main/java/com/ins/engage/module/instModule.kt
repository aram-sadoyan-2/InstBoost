package com.ins.engage.module

import com.ins.engage.repoimpl.InstMainRepoImpl
import com.ins.engage.repository.InstMainRepo
import com.ins.engage.viewModel.InstMainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val instModule = module {

    viewModel {
        InstMainViewModel(get(), get())
    }

    factory<InstMainRepo> {
        InstMainRepoImpl(get())
    }

}