package com.ins.boostyou.repository

import com.ins.boostyou.AppResult
import com.ins.boostyou.model.response.UserData

interface InstMainRepo {
    suspend fun getPostDataFromNewJson(userName: String, saveUserName: Boolean? = false): AppResult<UserData>
}