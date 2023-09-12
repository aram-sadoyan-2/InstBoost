package com.ins.engage.viewModel

import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ins.engage.AppResult
import com.ins.engage.model.request.InstAccessTokenRequestModel
import com.ins.engage.model.response.BaseResponse
import com.ins.engage.module.ProfDispatchers
import com.ins.engage.repository.InstMainRepo

class InstMainViewModel(
    private var repo: InstMainRepo,
    dispatchers: ProfDispatchers
) : BaseViewModel(dispatchers) {

    private val _accessToken = MutableLiveData<AppResult<BaseResponse>>()
    val accessToken: LiveData<AppResult<BaseResponse>>
        get() = _accessToken

    fun getAccessToken(instAccessTokenRequestModel: InstAccessTokenRequestModel) {
        launchOnBackground {
            _accessToken.postValue(repo.requestGoogleLogin(instAccessTokenRequestModel))
        }
    }

    private fun <T> MutableLiveData<T>.setValueSafe(value: T?) =
        if (Looper.getMainLooper() == Looper.myLooper()) this.value = value else postValue(value)

}