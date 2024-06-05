package com.dicoding.braincoresellerapp.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.braincoresellerapp.data.modal.Result
import com.dicoding.braincoresellerapp.data.repository.SellerRepository
import com.dicoding.braincoresellerapp.data.response.register.RegisterRequest
import com.dicoding.braincoresellerapp.data.response.register.RegisterResponse

class RegisterViewModel(private val sellerRepository: SellerRepository): ViewModel() {
    fun register(registerRequest: RegisterRequest): LiveData<Result<RegisterResponse>>{
        return sellerRepository.postRegister(registerRequest)
    }
}