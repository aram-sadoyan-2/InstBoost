package com.ins.boostyou.dialog

import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.ins.boostyou.R


private var redirect_url: String = ""
private var request_url: String = ""
private var authenticationListener: AuthenticationListener? = null

class AuthenticationDialog(context: Context, var listener: AuthenticationListener) :
    Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.auth_dialog)

        authenticationListener = listener
        redirect_url = context.resources.getString(R.string.redirect_url)

//        request_url = "https://api.instagram.com/oauth/authorize?client_id=" +
//                context.resources.getString(R.string.client_id) +
//                "&redirect_uri=" + redirect_url +
//                "&scope=user_profile,user_media&response_type=code"

//https://graph.instagram.com/v11.0/10218560180051171/media
        request_url = "https://api.instagram.com/oauth/authorize?client_id=" +
                context.resources.getString(R.string.client_id) +
                "&redirect_uri=" + redirect_url +
                "&response_type=code&scope=user_profile,user_media"


        val webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url?.startsWith(redirect_url) == true) {
                    dismiss()
                    return true
                }
                return false
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                //Log.d("dwd", "url = $url")
                listener.onTokenReceived(url.orEmpty())
                if (url?.contains("code=") == true) {
                    val uri = Uri.parse(url)

                    var accessToken = uri.encodedFragment
                    accessToken = accessToken?.substring(accessToken.lastIndexOf("=") + 1) ?: ""
                    Log.d("dwd", "ACCTOKEN $accessToken")

                }
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                Log.d("dwd", "ERROR ")

            }
        }

        val webView = findViewById<WebView>(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(request_url)
        webView.webViewClient = webViewClient
    }


}