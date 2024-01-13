package com.ins.boostyou.controller

import android.content.Context
import android.util.Log
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.koinApplication
import org.koin.mp.KoinPlatformTools

object InstKoinHolder {
    private var koin: KoinApplication? = null
    private var allowOverride: Boolean = false
    private var measuredItemsLog = ""
    var measurementConfig: MeasurementConfig? = null

    fun startKoin(
        context: Context,
        allowOverride: Boolean,
        block: KoinApplication.() -> Unit
    ): KoinApplication = koinApp(
        context,
    ).also(block).apply {
        this@InstKoinHolder.allowOverride = allowOverride
        allowOverride(allowOverride)
        KoinPlatformTools.defaultContext().startKoin(this)
    }

    private fun koinApp(context: Context): KoinApplication = koin ?: koinApplication {
        androidContext(context)
    }.also { koin = it }

    private fun <T : Any> Class<T>.logIfMeasurementEnabled(start: Long) {
        measurementConfig?.let {
            val duration = System.currentTimeMillis() - start
            if (it.isItemMeasurementEnabled && duration > it.measurementThreshold) {
                measuredItemsLog += "${simpleName ?: "Unknown"}: $duration ms (get)\n"
            }
        }
    }

    @JvmStatic
    fun getKoin(context: Context) = koin?.koin ?: koinApp(context).koin

    @JvmOverloads
    @JvmStatic
    fun <T : Any> inject(
        context: Context,
        clazz: Class<T>,
        qualifier: Qualifier? = null,
        parameters: ParametersDefinition? = null,
    ): Lazy<T> {
        return lazy(LazyThreadSafetyMode.NONE) {
            val start = System.currentTimeMillis()
            val value = getKoin(context).get<T>(clazz.kotlin, qualifier, parameters)
            clazz.logIfMeasurementEnabled(start)
            value
        }
    }

    @JvmOverloads
    @JvmStatic
    fun <T : Any> get(
        context: Context,
        clazz: Class<T>,
        qualifier: Qualifier? = null,
        parameters: ParametersDefinition? = null,
    ): T {
        val kClass = clazz.kotlin
        val start = System.currentTimeMillis()
        val value = getKoin(context).get<T>(
            clazz = kClass,
            qualifier = qualifier,
            parameters = parameters
        )
        clazz.logIfMeasurementEnabled(start)
        return value
    }

    @JvmStatic
    fun loadAndOverrideModules(modules: List<Module>) {
        koin?.run {
            allowOverride(true)
            modules(modules)
            allowOverride(allowOverride)
        }
    }

    @JvmStatic
    fun loadModules(modules: List<Module>) {
        koin?.modules(modules)
    }

    @JvmStatic
    fun loadModules(vararg modules: Module) {
        koin?.modules(*modules)
    }

    @JvmStatic
    fun unloadModules(modules: List<Module>) {
        unloadKoinModules(modules)
    }

    @JvmStatic
    fun getMeasuredItemsLog(): String {
        Log.d(InstKoinHolder::class.java.simpleName, measuredItemsLog)
        return measuredItemsLog
    }

}