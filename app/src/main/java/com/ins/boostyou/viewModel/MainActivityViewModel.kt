package com.ins.boostyou.viewModel

import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ins.boostyou.AppResult
import com.ins.boostyou.model.response.boostyou.RemotePackages
import com.ins.boostyou.module.ProfDispatchers
import com.ins.boostyou.repository.InstMainRepo
import com.ins.boostyou.model.response.UserData

class MainActivityViewModel(
    private var repo: InstMainRepo,
    dispatchers: ProfDispatchers
) : BaseViewModel(dispatchers) {

    private val _userPostData = MutableLiveData<AppResult<UserData>>()
    val userPostData: LiveData<AppResult<UserData>>
        get() = _userPostData

    private val _requestRemotePackages = MutableLiveData<AppResult<List<RemotePackages>>>()
    val requestRemotePackages: LiveData<AppResult<List<RemotePackages>>>
        get() = _requestRemotePackages

    fun requestDataFromNewJson() {
        launchOnBackground {
            _userPostData.postValue(repo.getPostDataFromNewJson("aramsadoy"))
        }
    }

//    fun requestRemotePackages() {
//        launchOnBackground {
//            _requestRemotePackages.postValue(repo.requestRemotePackages())
//        }
//    }

    private fun <T> MutableLiveData<T>.setValueSafe(value: T?) =
        if (Looper.getMainLooper() == Looper.myLooper()) this.value = value else postValue(value)

}