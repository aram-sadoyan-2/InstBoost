package com.ins.boostyou.api

import com.ins.boostyou.model.response.BaseResponse
import com.ins.boostyou.model.response.InstAccessTokenResponseModel
import com.ins.boostyou.model.response.InstUserMediaJs
import com.ins.boostyou.model.response.boostyou.RemotePackages
import com.ins.boostyou.model.response.InstPrData
import com.ins.boostyou.model.response.boostyou.LikesPriceListModel
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

    @GET("https://www.instagram.com/api/v1/users/web_profile_info/")
    suspend fun getPostDataFromNewJsonTest(
        @Query("username") userName: String,
        @Header("sec-fetch-dest") secFetchDest: String,
        @Header("sec-fetch-mode") secFetchMode: String,
        @Header("sec-fetch-site") secFetchSite: String,
        @Header("X-IG-App-ID") appId: Long,
        @Header("X-IG-WWW-Claim") claim: Int,
        @Header("X-Requested-With") requestedWith: String,
    ): String?

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





    @POST("/apiV2.php")
    suspend fun requestRemotePackages(
        @Header("action") action: String? = "getPriceList"
    ): RemotePackages?

    @POST("/apiV2.php")
    suspend fun createUserIfNotExist(
        @Header("action") action: String? = "createUserIfNotExist",
    ): BaseResponse?

    @POST("/apiV2.php")
    suspend fun getUserInfo(
        @Header("action") action: String? = "getUserInfo",
    ): UserInfo?

    @POST("/apiV2.php")
    suspend fun getLikePriceList(
        @Header("action") action: String? = "getLikePriceList",
    ): LikesPriceListModel?

    @POST("/apiV2.php")
    suspend fun getFollowerPriceList(
        @Header("action") action: String? = "getFollowerPriceList",
    ): LikesPriceListModel?

    @POST("/apiV2.php")
    suspend fun getCommentPriceList(
        @Header("action") action: String? = "getCommentPriceList",
    ): LikesPriceListModel?


    @POST("/apiV2.php")
    suspend fun subTrackUserCoinsIfSpent(
        @Header("action") action: String? = "subtrackUserCoinsIfSpent",
        @Field("coins_minused") coinsMinus: String,
    ): Any?





    @FormUrlEncoded
    @POST("/apiV2.php")
    suspend fun requestTask(
        @Header("action") action: String? = "boostyouRequest",
        @Field("task_type") taskType: Int? = null,
        @Field("service_url") serviceUrl: String? = null,
        @Field("quality") quality: Int? = null,
        @Field("count") count: Int? = null,
        @Field("user_name") userName: String? = null,
        @Field("comments") comments: List<String>? = null
    ): BaseResponse?




    @POST("validationRequestEndpoint")
    suspend fun validate(@Body requestBody: HashMap<String, String>): BaseResponse?

}
