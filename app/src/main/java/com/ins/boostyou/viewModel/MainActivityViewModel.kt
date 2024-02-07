package com.ins.boostyou.viewModel

import android.os.Looper
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.ins.boostyou.AppResult
import com.ins.boostyou.constants.enum.LoadingState
import com.ins.boostyou.module.ProfDispatchers
import com.ins.boostyou.repository.InstMainRepo
import com.ins.boostyou.model.response.UserData
import com.ins.boostyou.model.response.UserState
import com.ins.boostyou.model.response.boostyou.UserInfo
import com.ins.boostyou.repository.SignInUserRepo
import kotlinx.coroutines.launch
import androidx.compose.foundation.gestures.DraggableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainActivityViewModel(
    private var instMainRepo: InstMainRepo,
    private var signInUserRepo: SignInUserRepo,
    dispatchers: ProfDispatchers,
) : BaseViewModel(dispatchers) {

    var userData by mutableStateOf(UserData())
    var userInfo by mutableStateOf(UserInfo())

    init {
        viewModelScope.launch {
            loadingState = LoadingState.LOADING
          //  requestUserInfo()
            requestDataFromNewJson("aramsadoy")
            loadingState = null
        }
    }

    private suspend fun requestUserInfo() {
        //launchOnBackground {
            signInUserRepo.getUserInfo().apply {
                when (this) {
                    is AppResult.Success -> {
                        Log.d("dwd", "requestUserInfo ---- " + successData)
                        userInfo = successData
                    }

                    is AppResult.Error -> {
                        Log.d("dwd", "requestUserInfo Error")
                    }
                }
            }
       // }
    }

    suspend fun requestDataFromNewJson(userName: String? = null) {
       // launchOnBackground {
            instMainRepo.getPostDataFromNewJson(userName).apply {
                when (this) {
                    is AppResult.Success -> {
                        Log.d("dwd", "requestInstUserdata ---- " + successData)
                        userData = successData
                    }

                    is AppResult.Error -> {
                        Log.d("dwd", "requestInstUserdata Error")
                    }
                }
          //  }
        }
    }


    fun logOut() {
        userData = UserData(userState = UserState.DOES_NOT_EXIST)
        //todo move to signinRepo
        instMainRepo.logOutUser()
    }


    private val _tabIndex: MutableLiveData<Int> = MutableLiveData(0)
    val tabIndex: LiveData<Int> = _tabIndex
    val tabs = listOf("Simple", "Gold")

    var isSwipeToTheLeft: Boolean = false
    private val draggableState = DraggableState { delta ->
        isSwipeToTheLeft= delta > 0
    }

    private val _dragState = MutableLiveData<DraggableState>(draggableState)
    val dragState: LiveData<DraggableState> = _dragState

    fun updateTabIndexBasedOnSwipe() {
        _tabIndex.value = when (isSwipeToTheLeft) {
            true -> Math.floorMod(_tabIndex.value!!.plus(1), tabs.size)
            false -> Math.floorMod(_tabIndex.value!!.minus(1), tabs.size)
        }
    }

    fun updateTabIndex(i: Int) {
        _tabIndex.value = i
    }




    private fun <T> MutableLiveData<T>.setValueSafe(value: T?) =
        if (Looper.getMainLooper() == Looper.myLooper()) this.value = value else postValue(value)

}