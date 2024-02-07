package com.ins.boostyou.billing

import com.ins.boostyou.api.RetrofitPostServiceApi
import com.ins.boostyou.model.response.boostyou.RemotePackageItem
import com.ins.boostyou.model.response.boostyou.RemotePackages

class RemoteSettingsServiceImpl(val api: RetrofitPostServiceApi) : RemoteSettingsService {
    override suspend fun getRemoteSettings(): List<RemotePackageItem>? {
       return api.requestRemotePackages()?.remotePackages
    }
}