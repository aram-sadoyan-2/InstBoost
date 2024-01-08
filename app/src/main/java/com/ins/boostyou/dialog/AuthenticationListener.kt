package com.ins.boostyou.dialog

interface AuthenticationListener {
    fun onTokenReceived(authToken: String)
}