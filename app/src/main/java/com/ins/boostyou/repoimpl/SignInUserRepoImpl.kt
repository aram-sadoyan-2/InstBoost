package com.ins.boostyou.repoimpl

import android.content.Context
import android.util.Log
import com.ins.boostyou.AppResult
import com.ins.boostyou.api.RetrofitPostServiceApi
import com.ins.boostyou.handleSuccess
import com.ins.boostyou.model.response.boostyou.LikesPriceListModel
import com.ins.boostyou.model.response.boostyou.UserInfo
import com.ins.boostyou.repository.SignInUserRepo
import com.ins.boostyou.utils.UserRegisterStatus

class SignInUserRepoImpl(
    private val api: RetrofitPostServiceApi,
    private val context: Context
) : SignInUserRepo {

    override suspend fun getUserInfo(): AppResult<UserInfo> {
        return try {
            val response = api.getUserInfo()
            if (response == null) {
                AppResult.Error(Exception("empty data"))
            } else {
                handleSuccess(response)
            }
        } catch (e: Exception) {
            Log.d("dwd", "getUserInfo " + e.message)
            AppResult.Error(e)
        }
    }

    override suspend fun createUserIfNotExist(): UserRegisterStatus {
        return try {
            val response = api.createUserIfNotExist()
            response?.let {
                when (it.status) {
                    "done" -> {
                        UserRegisterStatus.USER_CREATED
                    }
                    "user_exist" -> UserRegisterStatus.USER_EXISTS
                    else -> UserRegisterStatus.ERROR
                }
            } ?: run {
                UserRegisterStatus.ERROR
            }
        } catch (e: Exception) {
            Log.d("dwd", "createUserIfNotExist " + e.message)
            UserRegisterStatus.ERROR
        }
    }
}