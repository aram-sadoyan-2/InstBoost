package com.ins.boostyou.billing

import com.ins.boostyou.model.response.boostyou.RemotePackageItem
import com.ins.boostyou.model.response.boostyou.RemotePackages

interface RemoteSettingsService {
    suspend fun getRemoteSettings() :  List<RemotePackageItem>?
}