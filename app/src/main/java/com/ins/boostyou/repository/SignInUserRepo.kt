package com.ins.boostyou.repository

import com.ins.boostyou.AppResult
import com.ins.boostyou.model.response.boostyou.UserInfo
import com.ins.boostyou.utils.UserRegisterStatus

interface SignInUserRepo {
    suspend fun createUserIfNotExist(userName:String): UserRegisterStatus
    suspend fun getUserInfo(userName:String): AppResult<UserInfo>
}
