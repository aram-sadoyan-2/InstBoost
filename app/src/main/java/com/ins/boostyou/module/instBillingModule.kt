package com.ins.boostyou.module

import com.ins.boostyou.billing.GoogleBillingServiceImpl
import com.ins.boostyou.billing.InstBoostPaymentService

import org.koin.dsl.module

val instBillingModule = module {
    factory<InstBoostPaymentService> {
        GoogleBillingServiceImpl(get(), get())
    }
}