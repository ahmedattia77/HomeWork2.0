package com.example.homework20.models

data class LoginResponse(
    val data: LoginData,
    val message: String,
    val status: Boolean,
    val token: String
)