package com.ins.boostyou.billing

import com.ins.boostyou.api.RetrofitPostServiceApi
import com.ins.boostyou.model.response.boostyou.RemotePackages

class RemoteSettingsServiceImpl(val api: RetrofitPostServiceApi) : RemoteSettingsService {
    override suspend fun getRemoteSettings(): List<RemotePackages>? {
       return api.requestRemotePackages().apply(::println)
    }
}