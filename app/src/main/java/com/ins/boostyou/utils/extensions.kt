package com.ins.boostyou.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

fun Boolean?.orFalse() = this ?: false

/**
 * @return true in case the boolean is null.
 */
fun Boolean?.orTrue() = this ?: true

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("message")
}