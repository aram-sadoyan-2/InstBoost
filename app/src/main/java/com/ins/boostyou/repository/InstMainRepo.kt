package com.ins.boostyou.repository

import com.ins.boostyou.AppResult
import com.ins.boostyou.model.response.boostyou.RemotePackages
import com.ins.boostyou.model.response.UserData

interface InstMainRepo {
    suspend fun getPostDataFromNewJson(): AppResult<UserData>
    suspend fun requestRemotePackages(): AppResult<List<RemotePackages>>
}