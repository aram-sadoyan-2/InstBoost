package com.ins.boostyou.viewModel

import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ins.boostyou.AppResult
import com.ins.boostyou.model.response.InstUserMediaJs
import com.ins.boostyou.model.response.boostyou.RemotePackages
import com.ins.boostyou.module.ProfDispatchers
import com.ins.boostyou.repository.InstMainRepo
import com.ins.boostyou.model.response.InstPrData

class MainActivityViewModel(
    private var repo: InstMainRepo,
    dispatchers: ProfDispatchers
) : BaseViewModel(dispatchers) {

    private val _userName = MutableLiveData<AppResult<String>>()
    val userName: LiveData<AppResult<String>>
        get() = _userName

    private val _userMedia = MutableLiveData<AppResult<InstUserMediaJs>>()
    val userMedia: LiveData<AppResult<InstUserMediaJs>>
        get() = _userMedia

    private val _userPostData = MutableLiveData<AppResult<InstPrData>>()
    val userPostData: LiveData<AppResult<InstPrData>>
        get() = _userPostData

    private val _requestRemotePackages = MutableLiveData<AppResult<List<RemotePackages>>>()
    val requestRemotePackages: LiveData<AppResult<List<RemotePackages>>>
        get() = _requestRemotePackages


//    fun requestInstUserBasicData(accessToken: String) {
//        launchOnBackground {
//            _userName.postValue(repo.requestInstUserBasicData(accessToken))
//        }
//    }

//    fun requestMedia() {
//        launchOnBackground {
//            _userMedia.postValue(repo.requestMedia())
//        }
//    }


    fun requestDataFromNewJson() {
        launchOnBackground {
            _userPostData.postValue(repo.getPostDataFromNewJson())
        }
    }

    fun requestRemotePackages() {
        launchOnBackground {
            _requestRemotePackages.postValue(repo.requestRemotePackages())
        }
    }


    private fun <T> MutableLiveData<T>.setValueSafe(value: T?) =
        if (Looper.getMainLooper() == Looper.myLooper()) this.value = value else postValue(value)

}