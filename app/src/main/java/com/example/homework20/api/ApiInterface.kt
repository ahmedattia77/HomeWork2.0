package com.example.homework20.api

import com.example.homework20.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

    @POST("login")
    @FormUrlEncoded
    suspend fun login(
        @Field("email") email: String?,
        @Field("password") password: String?,
    ): Response<LoginResponse>

    @POST("sign_up")
    @FormUrlEncoded
    suspend fun signUp(
        @Field("name") name: String?,
        @Field("email") email: String?,
        @Field("password") password: String?,
    ): Response<LoginResponse>

}