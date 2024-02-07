package com.ins.boostyou.billing

import com.ins.boostyou.model.response.boostyou.RemotePackageItem

interface RemoteSettingsService {
    suspend fun getRemoteSettings() :  List<RemotePackageItem>?
}