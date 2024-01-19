package com.ins.boostyou.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.ins.boostyou.AppResult
import com.ins.boostyou.databinding.ActivityMainBinding
import com.ins.boostyou.viewModel.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private lateinit var bindingView: ActivityMainBinding
    private val mainActivityViewModel: MainActivityViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingView = ActivityMainBinding.inflate(layoutInflater)
        val view = bindingView.root
        setContentView(view)
        Log.d("dwd","MainActivity start")
        requestInstUserdata()
       // requestPackages()
       // setUpViews()
    }

    private fun setUpViews() {

    }

    private fun requestInstUserdata() {
        mainActivityViewModel.requestDataFromNewJson()
        mainActivityViewModel.userPostData.observe(this) {
            it?.let {
                when (it) {
                    is AppResult.Success -> {
                        Log.d("dwd", "requestInstUserdata ---- " + it.successData)
                        initViews()
                    }

                    is AppResult.Error -> {
                        Log.d("dwd", "requestInstUserdata Error")
                    }

                    else -> {}
                }
            }
        }
    }

    private fun requestPackages() {
        mainActivityViewModel.requestRemotePackages()
        mainActivityViewModel.requestRemotePackages.observe(this) {
            it?.let {
                when (it) {
                    is AppResult.Success -> {
                        Log.d("dwd", "requestInstUserdata ---- " + it.successData)
                    }

                    is AppResult.Error -> {
                        Log.d("dwd", "requestInstUserdata Error")
                    }
                }
            }
        }
    }

    private fun initViews() {

    }
}