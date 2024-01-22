package com.ins.boostyou.utils

fun Boolean?.orFalse() = this ?: false

/**
 * @return true in case the boolean is null.
 */
fun Boolean?.orTrue() = this ?: true