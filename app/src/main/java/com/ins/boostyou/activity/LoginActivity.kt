package com.ins.boostyou.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ins.boostyou.AppResult
import com.ins.boostyou.R
import com.ins.boostyou.databinding.ActivityLoginBinding
import com.ins.boostyou.dialog.AuthenticationDialog
import com.ins.boostyou.dialog.AuthenticationListener
import com.ins.boostyou.model.request.InstAccessTokenRequestModel
import com.ins.boostyou.ui.theme.InstBoostTheme
import com.ins.boostyou.utils.extractCodeFromJsonUrl
import com.ins.boostyou.viewModel.InstMainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : ComponentActivity(), AuthenticationListener {
    private lateinit var bindingView: ActivityLoginBinding
    private val instMainViewModel: InstMainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingView = ActivityLoginBinding.inflate(layoutInflater)
        val view = bindingView.root
        setContentView(view)

        bindingView.btn.setOnClickListener {
            loginInstagram()
        }

    }

//    private fun getInstUserName() {
//        val accessToken = getTokenFromLocal(applicationContext)
//        if (accessToken.isNotEmpty()) {
//            Log.d("dwd", "LongLiveAccessToken From Pref $accessToken")
//            instMainViewModel.requestInstData(accessToken)
//            instMainViewModel.userName.observe(this) {
//                it?.let {
//                    when (it) {
//                        is AppResult.Success -> {
//                            Log.d("dwd", "UserName ACTIVITY ${it.successData}")
//                            bindingView.userNameTxtView.text = it.successData
//                          //  getInstUserMedia()
//                        }
//
//                        is AppResult.Error -> {
//                           // Log.d("dwd", "UserName ACTIVITY Error")
//                        }
//                    }
//                }
//            }
//        }
//    }

//    private fun getInstUserMedia() {
//        val accessToken = getTokenFromLocal(applicationContext)
//        if (accessToken.isNotEmpty()) {
//           // Log.d("dwd", "LongLiveAccessToken From Pref $accessToken")
//            instMainViewModel.requestMedia()
//            instMainViewModel.userMedia.observe(this) {
//                it?.let {
//                    when (it) {
//                        is AppResult.Success -> {
//                            Log.d("dwd","D#d3d3d3d " + it.successData.data[0].permalink)
//                           // bindingView.userNameTxtView.text = it.successData
//                            //getLikeCount(it.successData.data[0].permalink)
//                        }
//
//                        is AppResult.Error -> {
//                            Log.d("dwd", "requestMedia ACTIVITY Error")
//                        }
//                    }
//                }
//            }
//        }
//    }

//    private fun getLikeCount(permalink: String) {
//        instMainViewModel.parseUr(permalink)
//        val input = File("/tmp/input.html")
//        //val doc: Document = Jsoup.parse(input, "UTF-8", permalink)
//
//    }

    private fun loginInstagram() {
        val authenticationDialog = AuthenticationDialog(this, this)
        authenticationDialog.setCancelable(true)
        authenticationDialog.show()
    }

    override fun onTokenReceived(authToken: String) {
        val a = Uri.parse(authToken)
        val finalUrToken = extractCodeFromJsonUrl(authToken)
        Log.d("dwd", "%%%% $finalUrToken")
        Log.d("dwd", "$$$$ $authToken")
        a.getQueryParameter("code").let(::println)
        if (finalUrToken.isNotEmpty()) {
            requestShortInstAccsToken(finalUrToken)
        }
    }

    private fun requestShortInstAccsToken(authToken: String) {
        val clientId = resources.getString(R.string.client_id)
        val clientSecret = resources.getString(R.string.client_secret)
        val redirectUrl = resources.getString(R.string.redirect_url)
        instMainViewModel.requestShortInstAccsToken(
            InstAccessTokenRequestModel(
                client_id = clientId,
                client_secret = clientSecret,
                grant_type = "authorization_code",
                redirect_uri = redirectUrl,
                code = authToken
            )
        )
        instMainViewModel.accessToken.observe(this) {
            it?.let {
                when (it) {
                    is AppResult.Success -> {
                        Log.d("dwd", "Success requestInstAccSToken ---- " + it.successData)
                        requestLongLiveAccessToken(it.successData, clientSecret)
                    }

                    is AppResult.Error -> {
                        Log.d("dwd", "Error")
                    }
                }
            }
        }
    }

    private fun requestLongLiveAccessToken(shortLiveAccessToken: String, clientSecret: String) {
        instMainViewModel.requestLongLiveInstAccessToken(
            InstAccessTokenRequestModel(
                shortLiveAccessToken = shortLiveAccessToken,
                client_secret = clientSecret
            )
        )

        instMainViewModel.longAccessToken.observe(this) {
            it?.let {
                when (it) {
                    is AppResult.Success -> {
                        Log.d("dwd", "LongLiveAccessToken Success " + it.successData)
                        requestInstUserBasicData(it.successData)
                    }

                    is AppResult.Error -> {
                        Log.d("dwd", "LongLiveAccessToken Error")
                    }
                }
            }
        }
    }

    private fun requestInstUserBasicData(longLiveAccessToken: String) {
        instMainViewModel.requestInstUserBasicData(longLiveAccessToken)
        instMainViewModel.longAccessToken.observe(this) {
            it?.let {
                when (it) {
                    is AppResult.Success -> {
                        Log.d("dwd", "Success requestInstUserBasicData--- " + it.successData)
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    }

                    is AppResult.Error -> {
                        Log.d("dwd", "Error requestInstUserBasicData")
                    }
                }
            }
        }
    }



}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InstBoostTheme {
        Greeting("Android")
    }
}