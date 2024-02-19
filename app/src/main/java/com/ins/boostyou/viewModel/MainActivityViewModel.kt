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
import com.ins.boostyou.model.request.BoostYouTaskRequest
import com.ins.boostyou.model.response.BaseResponse
import com.ins.boostyou.model.response.UserMediaInfoList
import com.ins.boostyou.model.response.boostyou.LikesPriceItem
import com.ins.boostyou.model.response.boostyou.LikesPriceListModel
import com.ins.boostyou.repository.PaymentActionRepo

class MainActivityViewModel(
    private var instMainRepo: InstMainRepo,
    private var signInUserRepo: SignInUserRepo,
    private var repo: PaymentActionRepo,
    dispatchers: ProfDispatchers,
) : BaseViewModel(dispatchers) {

    var userData by mutableStateOf(UserData())
    var userInfo by mutableStateOf(UserInfo())
    var likeCoast by mutableStateOf(LikesPriceListModel())
    var followerCoast by mutableStateOf(LikesPriceListModel())
    var goldFollowerCoast by mutableStateOf(LikesPriceListModel())
    var goldLikeCoast by mutableStateOf(LikesPriceListModel())
    var commentsCoast by mutableStateOf(LikesPriceListModel())

    var boostYouTaskRequest = BoostYouTaskRequest()
    var taskResultData by mutableStateOf(BaseResponse())

    init {
        viewModelScope.launch {
            requestData()
        }
    }

    private suspend fun requestData() {
        loadingState = LoadingState.LOADING
        signInUserRepo.createUserIfNotExist().let {
            requestServiceCoasts()
            requestUserInfo()
            requestDataFromNewJson()
            loadingState = null
        }
    }

    private suspend fun requestServiceCoasts() {
        when (val result = instMainRepo.requestLikePrices()) {
            is AppResult.Success -> likeCoast = result.successData
            else -> {}
        }
        goldLikeCoast =
            likeCoast.copy(data = likeCoast.data?.map {
                LikesPriceItem(
                    count = it.count.or(0).times(2), price = it.price?.times(
                        2
                    )
                )
            })

        when (val result = instMainRepo.requestFollowerPrices()) {
            is AppResult.Success -> followerCoast = result.successData
            else -> {}
        }
        goldFollowerCoast =
            followerCoast.copy(data = followerCoast.data?.map {
                LikesPriceItem(
                    count = it.count.or(0).times(
                        2
                    ), price = it.price?.times(2)
                )
            })

        when (val result = instMainRepo.requestCommentPrices()) {
            is AppResult.Success -> commentsCoast = result.successData
            else -> {}
        }
    }

    private suspend fun requestUserInfo() {
        signInUserRepo.getUserInfo().apply {
            when (this) {
                is AppResult.Success -> {
                    Log.d("dwd", "requestUserInfo ---- $successData")
                    userInfo = successData
                }

                is AppResult.Error -> {
                    Log.d("dwd", "requestUserInfo Error")
                }
            }
        }
    }

    suspend fun requestDataFromNewJson(
        userName: String? = null,
        saveUserName: Boolean? = false,
        showLoading: Boolean? = false,
    ) {
        if (showLoading == true) {
            loadingState = LoadingState.LOADING
        }
        when (val result = instMainRepo.getPostDataFromNewJson(userName, saveUserName)) {
            is AppResult.Success -> {
                userData = result.successData
                boostYouTaskRequest.userName = userData.userName
                if (showLoading == true) {
                    loadingState = null
                }
            }

            else -> {
                if (showLoading == true) {
                    loadingState = null
                }
            }
        }
    }

    suspend fun logOut() {
        userData = UserData(userState = UserState.DOES_NOT_EXIST)
        //todo move to signinRepo
        instMainRepo.logOutUser()
    }

    private val _tabIndex: MutableLiveData<Int> = MutableLiveData(0)
    val tabIndex: LiveData<Int> = _tabIndex
    val tabs = listOf("Simple", "Gold")

    var isSwipeToTheLeft: Boolean = false
    private val draggableState = DraggableState { delta ->
        isSwipeToTheLeft = delta > 0
    }

    private val _dragState = MutableLiveData(draggableState)
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


    fun requestTask() {
        Log.d("dwd", "TaskResultData $boostYouTaskRequest")
        launchOnBackground {
           // repo.requestTask(boostYouTaskRequest).collect {
               //  taskResultData = it
                //  Log.d("dwd","TaskResultData $it")
          //  }
        }
    }

    fun setImageUrlToBoostYouRequest(
        page: Int,
        userMediaInfoList: MutableList<UserMediaInfoList?>
    ) {
        when (page) {
            0 -> boostYouTaskRequest.serviceUrl = null
            else -> boostYouTaskRequest.serviceUrl = userMediaInfoList[page]?.postUrl
        }
    }
}