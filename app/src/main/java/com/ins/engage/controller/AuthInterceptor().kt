package com.ins.engage.controller

import android.content.Context

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor(
    val context: Context,
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        return response
    }

}