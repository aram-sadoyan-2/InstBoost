package com.ins.boostyou.repository

import com.ins.boostyou.AppResult
import com.ins.boostyou.model.response.boostyou.LikesPriceListModel
import com.ins.boostyou.model.response.boostyou.UserInfo
import com.ins.boostyou.utils.UserRegisterStatus

interface SignInUserRepo {
    suspend fun getUserInfo(): AppResult<UserInfo>
    suspend fun createUserIfNotExist(): UserRegisterStatus

}
