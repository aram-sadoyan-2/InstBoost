package com.ins.boostyou.api

import com.ins.boostyou.model.response.BaseResponse
import com.ins.boostyou.model.response.InstAccessTokenResponseModel
import com.ins.boostyou.model.response.InstUserMediaJs
import com.ins.boostyou.model.response.boostyou.RemotePackages
import com.ins.boostyou.model.response.InstPrData
import com.ins.boostyou.model.response.boostyou.UserInfo
import retrofit2.http.*


interface RetrofitPostServiceApi {

    @GET("https://www.instagram.com/api/v1/users/web_profile_info/")
    suspend fun getPostDataFromNewJson(
        @Query("username") userName: String,
        @Header("sec-fetch-dest") secFetchDest: String,
        @Header("sec-fetch-mode") secFetchMode: String,
        @Header("sec-fetch-site") secFetchSite: String,
        @Header("X-IG-App-ID") appId: Long,
        @Header("X-IG-WWW-Claim") claim: Int,
        @Header("X-Requested-With") requestedWith: String,
    ): InstPrData?

    @FormUrlEncoded
    @POST("oauth/access_token")
    suspend fun requestShortInstAccsToken(
        //@Body instAccessTokenRequestModel: InstAccessTokenRequestModel,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("redirect_uri") redirectUri: String,
        @Field("code") code: String,
        @Field("grant_type") grantType: String,
    ): InstAccessTokenResponseModel?

    @GET("https://graph.instagram.com/access_token")
    suspend fun requestLongLiveInstAccessToken(
        @Query("grant_type") grantType: String,
        @Query("client_secret") clientSecret: String,
        @Query("access_token") shortLiveAccessToken: String,
    ): InstAccessTokenResponseModel?

    @GET("https://graph.instagram.com/me")
    suspend fun requestInstUserBasicData(
        @Query("fields") fields: String,
        @Query("access_token") accessToken: String
    ): InstAccessTokenResponseModel?

    @GET("https://graph.instagram.com/me/media")
    suspend fun requestMedia(
        @Query("fields") fields: String,
        @Query("access_token") accessToken: String
    ): InstUserMediaJs?






    @FormUrlEncoded
    @POST("/api.php")
    suspend fun requestRemotePackages(
        @Field("action") action: String? = "getPriceList"
    ): List<RemotePackages>?

    @FormUrlEncoded
    @POST("/api.php")
    suspend fun createUserIfNotExist(
        @Field("user_name") userName: String,
        @Field("action") action: String? = "createUserIfNotExist",
    ): BaseResponse?

    @FormUrlEncoded
    @POST("/api.php")
    suspend fun getUserInfo(
        @Field("user_name") userName: String,
        @Field("action") action: String? = "getUserInfo",
    ): UserInfo?

    @FormUrlEncoded
    @POST("/api.php")
    suspend fun getLikePriceList(
        @Field("action") action: String? = "getLikePriceList",
    ): Any?

    @FormUrlEncoded
    @POST("/api.php")
    suspend fun getFollowerPriceList(
        @Field("action") action: String? = "getFollowerPriceList",
    ): Any?

    @FormUrlEncoded
    @POST("/api.php")
    suspend fun requestBoostTask(
        @Field("user_name") userName: String,
        @Field("task_typ") taskType: String,
        @Field("type") type: String,
        @Field("count") count: Int,
        @Field("price") price: Double,
        @Field("service_url") serviceUrl: String,
        @Field("action") action: String? = "boostyouRequest",
    ): Any?




    @POST("validationRequestEndpoint")
    suspend fun validate(@Body requestBody: HashMap<String, String>): BaseResponse?

}
