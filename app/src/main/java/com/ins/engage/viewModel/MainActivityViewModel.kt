package com.ins.engage.viewModel

import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ins.engage.AppResult
import com.ins.engage.model.response.InstPrData
import com.ins.engage.model.response.InstUserMediaJs
import com.ins.engage.model.response.InstaProfileModel
import com.ins.engage.module.ProfDispatchers
import com.ins.engage.repository.InstMainRepo

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


    fun requestInstUserBasicData(accessToken: String) {
        launchOnBackground {
            _userName.postValue(repo.requestInstUserBasicData(accessToken))
        }
    }

    fun requestMedia() {
        launchOnBackground {
            _userMedia.postValue(repo.requestMedia())
        }
    }


    fun requestDataFromNewJson() {
        launchOnBackground {
            _userPostData.postValue(repo.getPostDataFromNewJson())
        }
    }


    private fun <T> MutableLiveData<T>.setValueSafe(value: T?) =
        if (Looper.getMainLooper() == Looper.myLooper()) this.value = value else postValue(value)

}