package com.ins.boostyou

sealed class AppResult<out T> {

    data class Success<out T>(val successData: T) : AppResult<T>()
    class Error(
        private val exception: java.lang.Exception,
        val message: String = exception.localizedMessage
    ) : AppResult<Nothing>()
}

fun <T : Any> handleApiError(resp: T): AppResult.Error {
    return AppResult.Error(Exception(resp.toString()))
}

fun <T : Any> handleSuccess(response: List<T>): AppResult<List<T>> {
    response.let {
        return AppResult.Success(it)
    }
}

fun <T : Any> handleSuccess(response: T): AppResult<T> {
    response.let {
        return AppResult.Success(it)
    }
}