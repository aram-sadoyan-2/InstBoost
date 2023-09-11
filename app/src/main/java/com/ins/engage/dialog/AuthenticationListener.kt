package com.ins.engage.dialog

interface AuthenticationListener {
    fun onTokenReceived(authToken: String)
}