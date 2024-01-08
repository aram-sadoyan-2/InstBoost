package com.ins.boostyou.module

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val DISPATCHER_IO = "DISPATCHER_IO"
const val DISPATCHER_UI = "DISPATCHER_UI"
const val DISPATCHER_DEFAULT = "DISPATCHER_DEFAULT"

object ProfDispatchersImpl : ProfDispatchers {
    override val ioDispatcher: CoroutineDispatcher by lazy { Dispatchers.IO }
    override val mainDispatcher: CoroutineDispatcher by lazy { Dispatchers.Main.immediate }
    override val defaultDispatcher: CoroutineDispatcher by lazy { Dispatchers.Default }
}

val coroutineModule = module {
    single(named(DISPATCHER_IO)) { ProfDispatchersImpl.ioDispatcher }
    single(named(DISPATCHER_UI)) { ProfDispatchersImpl.mainDispatcher }
    single(named(DISPATCHER_DEFAULT)) { ProfDispatchersImpl.defaultDispatcher }
    single<ProfDispatchers> { ProfDispatchersImpl }
}