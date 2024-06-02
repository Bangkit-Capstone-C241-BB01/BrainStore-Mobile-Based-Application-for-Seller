package com.dicoding.braincoresellerapp.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dicoding.braincoresellerapp.data.modal.Result
import com.dicoding.braincoresellerapp.data.response.RegisterRequest
import com.dicoding.braincoresellerapp.data.response.RegisterResponse
import com.dicoding.braincoresellerapp.data.response.login.LoginRequest
import com.dicoding.braincoresellerapp.data.response.login.LoginResponse
import com.dicoding.braincoresellerapp.data.retrofit.ApiConfig
import com.dicoding.braincoresellerapp.utils.Preference
import kotlinx.coroutines.flow.first

class SellerRepository (private val context: Context) {

    private val apiService = ApiConfig.getApiService()

    fun postRegister(registerRequest: RegisterRequest): LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.register(registerRequest)
            emit(Result.Success(response))
        }catch (e: Exception){
            Log.e("SellerRepository", "postRegister: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun postLogin(loginRequest: LoginRequest): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.login(loginRequest)
            emit(Result.Success(response))
        }catch (e: Exception){
            Log.e("SellerRepository", "postLogin: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    suspend fun logOut() {
        Preference.logOut(context)
    }

    fun getToken(): LiveData<String?> = liveData {
        val token = Preference.getToken(context).first()
        Log.d("StoryRepository", "Token: $token")
        emit(token)
    }
}