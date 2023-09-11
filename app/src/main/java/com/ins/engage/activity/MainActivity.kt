package com.ins.engage.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ins.engage.databinding.ActivityMainBinding
import com.ins.engage.dialog.AuthenticationDialog
import com.ins.engage.dialog.AuthenticationListener
import com.ins.engage.ui.theme.InstBoostTheme


class MainActivity : ComponentActivity(), AuthenticationListener {
    private lateinit var bindingView: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingView = ActivityMainBinding.inflate(layoutInflater)
        val view = bindingView.root
        setContentView(view)

        bindingView.btn.setOnClickListener {
            loginInstagram()
        }
//        setContent {
//            InstBoostTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                    Button(onClick = {
//                        Log.d("dwd","kmdqwndqwdqf")
//                    }) {
//                        Log.d("dwd","dewfwef")
//                    }
//                }
//            }
//        }
    }

    private fun loginInstagram() {
        val authenticationDialog = AuthenticationDialog(this, this)
        authenticationDialog.setCancelable(true)
        authenticationDialog.show()
    }

    override fun onTokenReceived(authToken: String) {
        Log.d("dwd", "OnTokenReceived $authToken")
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