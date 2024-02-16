package com.ins.boostyou.module
import android.content.Context
import android.provider.Settings
import android.util.Log
import com.ins.boostyou.AppConstants
import com.ins.boostyou.api.RetrofitPostServiceApi
import com.ins.boostyou.controller.AuthInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

fun networkModule(context: Context) = module {

    factory {
        AuthInterceptor(context)
    }
    factory { provideOkHttpClient(get(), context) }
    factory { provideForecastApi(get()) }
    single { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(AppConstants.BOOST_YOU_BASE_URL).client(okHttpClient)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideOkHttpClient(authInterceptor: AuthInterceptor, context: Context): OkHttpClient {
    val clientBuilder = OkHttpClient.Builder()
    val dId =
        Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    Log.d("dwd","Device_id $dId")
    clientBuilder.addInterceptor { chain ->
        val newRequest = chain.request().newBuilder()
            .addHeader("device_id", dId)
            .build()
        chain.proceed(newRequest)
    }
    return clientBuilder.addInterceptor(authInterceptor).build()
}

//fun provideOkHttpClient(authInterceptor: AuthInterceptor, context: Context): OkHttpClient {
//    return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
//}

fun provideForecastApi(retrofit: Retrofit): RetrofitPostServiceApi = retrofit.create(RetrofitPostServiceApi::class.java)

