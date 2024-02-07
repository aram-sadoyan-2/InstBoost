package com.ins.boostyou.viewModel

import android.os.Looper
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import com.ins.boostyou.AppResult
import com.ins.boostyou.module.ProfDispatchers
import com.ins.boostyou.repository.InstMainRepo
import com.ins.boostyou.model.response.UserData
import com.ins.boostyou.model.response.UserState

class MainActivityViewModel(
    private var repo: InstMainRepo,
    dispatchers: ProfDispatchers
) : BaseViewModel(dispatchers) {

    var userData by mutableStateOf(UserData())

    init {
        requestDataFromNewJson()
    }

    private fun requestDataFromNewJson(userName: String? = "vrdrobe.app") {
        launchOnBackground {
            repo.getPostDataFromNewJson(userName).apply {
                when (this) {
                    is AppResult.Success -> {
                        Log.d("dwd", "requestInstUserdata ---- " + successData)
                        userData = successData
                    }

                    is AppResult.Error -> {
                        Log.d("dwd", "requestInstUserdata Error")
                    }
                }
            }
        }
    }


    fun logOut() {
        userData = UserData(userState = UserState.DOES_NOT_EXIST)
        repo.logOutUser()
    }

    private fun <T> MutableLiveData<T>.setValueSafe(value: T?) =
        if (Looper.getMainLooper() == Looper.myLooper()) this.value = value else postValue(value)

}