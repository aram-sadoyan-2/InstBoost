package com.ins.boostyou.repository

import com.ins.boostyou.AppResult
import com.ins.boostyou.model.response.UserData
import com.ins.boostyou.model.response.boostyou.LikesPriceListModel

interface InstMainRepo {
    suspend fun getPostDataFromNewJson(userName: String?, saveUserName: Boolean? = false): AppResult<UserData>
    suspend fun requestLikePrices(): AppResult<LikesPriceListModel>
    suspend fun requestFollowerPrices(): AppResult<LikesPriceListModel>
    suspend fun requestCommentPrices(): AppResult<LikesPriceListModel>
    fun logOutUser()
}