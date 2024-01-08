package com.ins.boostyou.viewModel

import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ins.boostyou.AppResult
import com.ins.boostyou.model.request.InstAccessTokenRequestModel
import com.ins.boostyou.model.response.InstUserMediaJs
import com.ins.boostyou.module.ProfDispatchers
import com.ins.boostyou.repository.InstMainRepo
import org.jsoup.Jsoup

class InstMainViewModel(
    private var repo: InstMainRepo,
    dispatchers: ProfDispatchers
) : BaseViewModel(dispatchers) {

    private val _accessToken = MutableLiveData<AppResult<String>>()
    val accessToken: LiveData<AppResult<String>>
        get() = _accessToken


    private val _longAccessToken = MutableLiveData<AppResult<String>>()
    val longAccessToken: LiveData<AppResult<String>>
        get() = _longAccessToken

    private val _userName = MutableLiveData<AppResult<String>>()
    val userName: LiveData<AppResult<String>>
        get() = _userName

    private val _userMedia = MutableLiveData<AppResult<InstUserMediaJs>>()
    val userMedia: LiveData<AppResult<InstUserMediaJs>>
        get() = _userMedia

    fun requestShortInstAccsToken(instAccessTokenRequestModel: InstAccessTokenRequestModel) {
        launchOnBackground {
            _accessToken.postValue(repo.requestShortInstAccsToken(instAccessTokenRequestModel))
        }
    }

    fun requestLongLiveInstAccessToken(instAccessTokenRequestModel: InstAccessTokenRequestModel) {
        launchOnBackground {
            _longAccessToken.postValue(repo.requestLongLiveInstAccessToken(instAccessTokenRequestModel))
        }
    }

    fun requestInstUserBasicData(accessToken: String) {
        launchOnBackground {
            _userName.postValue(repo.requestInstUserBasicData(accessToken))
        }
    }

    fun requestMedia() {
        launchOnBackground {
            _userMedia.postValue(repo.requestMedia())
        }
    }

    fun parseUr(ur:String) {
        launchOnBackground {
            val doc = Jsoup.connect(ur).get()
            val doc2 = Jsoup.connect("https://www.google.com/").get()
            val content = doc.getElementById("content")
            val content2 = doc2.getElementById("content")
            val likeCount = doc.child(0).child(1).child(32).toString().split("FEEDBACK_LIKE_SUBSCRIBE")[1][2]
            Log.d("dwd", "Like Count $likeCount")
        }
    }

    private fun <T> MutableLiveData<T>.setValueSafe(value: T?) =
        if (Looper.getMainLooper() == Looper.myLooper()) this.value = value else postValue(value)

}