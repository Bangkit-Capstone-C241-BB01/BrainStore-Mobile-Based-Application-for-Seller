package com.dicoding.braincoresellerapp.data.retrofit

import com.dicoding.braincoresellerapp.data.response.RegisterRequest
import com.dicoding.braincoresellerapp.data.response.RegisterResponse
import com.dicoding.braincoresellerapp.data.response.login.LoginRequest
import com.dicoding.braincoresellerapp.data.response.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST



interface ApiService {

    @POST("register")
    suspend fun register(
        @Body request: RegisterRequest
    ): RegisterResponse

    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse
}

