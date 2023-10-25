package com.ins.engage.api

import com.ins.engage.model.response.InstAccessTokenResponseModel
import com.ins.engage.model.response.InstUserMediaJs
import com.ins.engage.model.response.InstaProfileModel
import retrofit2.Call
import retrofit2.http.*


interface RetrofitPostServiceApi {

    @GET
    suspend fun getPostData(@Url str: String?): InstaProfileModel?

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
