package com.dicoding.braincoresellerapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.braincoresellerapp.data.modal.Result
import com.dicoding.braincoresellerapp.data.repository.SellerRepository
import com.dicoding.braincoresellerapp.data.response.login.LoginRequest
import com.dicoding.braincoresellerapp.data.response.login.LoginResponse

class LoginViewModel(private  val sellerRepository: SellerRepository): ViewModel() {
    fun login(loginRequest: LoginRequest): LiveData<Result<LoginResponse>> {
        return sellerRepository.postLogin(loginRequest)
    }

    fun getToken() = sellerRepository.getToken()
}