package com.ins.boostyou.module

import kotlinx.coroutines.CoroutineDispatcher

interface ProfDispatchers {
    val ioDispatcher: CoroutineDispatcher
    val mainDispatcher: CoroutineDispatcher
    val defaultDispatcher: CoroutineDispatcher
}