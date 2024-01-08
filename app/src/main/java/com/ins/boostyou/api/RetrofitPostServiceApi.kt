package com.ins.boostyou.api

import com.ins.boostyou.model.response.InstAccessTokenResponseModel
import com.ins.boostyou.model.response.InstUserMediaJs
import retrofit2.http.*


interface RetrofitPostServiceApi {

    @GET("https://www.instagram.com/api/v1/users/web_profile_info/")
    suspend fun getPostDataFromNewJson(
        @Query("username") userName: String,
       // @Query("fbclid") fbclId: String,
        @Header("sec-fetch-dest") secFetchDest: String,
        @Header("sec-fetch-mode") secFetchMode: String,
        @Header("sec-fetch-site") secFetchSite: String,
        @Header("X-IG-App-ID") appId: Long,
        @Header("X-IG-WWW-Claim") claim: Int,
        @Header("X-Requested-With") requestedWith: String,
    ): Any?

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

}
