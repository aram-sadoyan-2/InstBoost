package com.ins.boostyou.viewModel

import androidx.lifecycle.MutableLiveData
import com.ins.boostyou.AppResult
import com.ins.boostyou.model.response.boostyou.UserInfo
import com.ins.boostyou.module.ProfDispatchers
import com.ins.boostyou.repository.SignInUserRepo
import com.ins.boostyou.utils.UserRegisterStatus

class SignInUserViewModel(
    private var repo: SignInUserRepo,
    dispatchers: ProfDispatchers
) : BaseViewModel(dispatchers) {

    private val _userRegistrationStatus = MutableLiveData<UserRegisterStatus>()
    val userRegistrationStatus = _userRegistrationStatus

    private val _userInfo = MutableLiveData<AppResult<UserInfo>>()
    val userInfo = _userInfo

    init {
       // createUserIfNotExist()
    }

    private fun createUserIfNotExist() {
        launchOnBackground {
            repo.createUserIfNotExist()
        }
    }


}