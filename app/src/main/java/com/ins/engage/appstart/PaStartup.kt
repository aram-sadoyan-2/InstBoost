///*
// * (c) 2023 Picsart, Inc.  All rights reserved.
// */
//
//package com.ins.engage.appstart
//
//import android.content.Context
//import android.util.Log
//import com.bugsnag.android.Bugsnag
//import com.picsart.startup.AndroidStartup
//import com.picsart.studio.common.crash.CrashWrapper
//import java.util.concurrent.Executor
//
//abstract class PaStartup<T> : AndroidStartup<T>() {
//
//    protected open val canCrash = false
//
//    override val awaitTime: Long = 5
//
//    abstract fun initialize(context: Context): T?
//
//    override fun createExecutor(): Executor = PaStartupExecutors.backgroundExecutor
//
//    override fun create(context: Context): T? {
//        Log.d("dwd","PaStartup create")
//        val threadName = Thread.currentThread().name
//        val start = System.currentTimeMillis()
//
//        if (CrashWrapper.isStarted()) {
//            CrashWrapper.leaveBreadcrumb("$name: start", mapOf(
//                KEY_THREAD_NAME to threadName,
//                KEY_BLOCK_MAIN_THREAD to waitOnMainThread(),
//                KEY_STARTED_AT to start
//            ))
//        }
//
//        val result = if (canCrash) {
//            runCatching { initialize(context) }.getOrNull()
//        } else {
//            initialize(context)
//        }
//
//        if (CrashWrapper.isStarted()) {
//            CrashWrapper.leaveBreadcrumb("$name: end", mapOf(
//                KEY_STATE to if(result == null) "failed" else "initialized",
//                KEY_DURATION to (System.currentTimeMillis() - start)
//            ))
//        }
//
//        return  result
//    }
//
//
//    companion object {
//        private const val KEY_THREAD_NAME = "thread_name"
//        private const val KEY_BLOCK_MAIN_THREAD = "block_main_thread"
//        private const val KEY_STARTED_AT = "started_at"
//        private const val KEY_STATE = "state"
//        private const val KEY_DURATION = "duration"
//    }
//}
