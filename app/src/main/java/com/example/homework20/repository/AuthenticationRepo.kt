package com.example.homework20.repository

import com.example.homework20.api.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthenticationRepo {

    private val apiInterface = RetrofitHelper.getInstance().create(ApiInterface::class.java)

    suspend fun login(email: String, password: String) = apiInterface.login(email, password)
    suspend fun signUp(name:String, email: String, password: String)
                        = apiInterface.signUp(name,email, password,)
}

object RetrofitHelper {

     private const val baseUrl = "https://android-training.appssquare.com/api/"

     fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
     }
}