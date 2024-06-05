package com.dicoding.braincoresellerapp.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.braincoresellerapp.data.modal.Result
import com.dicoding.braincoresellerapp.data.repository.SellerRepository
import com.dicoding.braincoresellerapp.data.response.account.SellerResponse
import kotlinx.coroutines.launch

class SettingViewModel(private val repository: SellerRepository): ViewModel() {

    fun getSellerAccount() : LiveData<Result<SellerResponse>> = repository.getSellerAccount()
    fun logout(){
        viewModelScope.launch {
            repository.logOut()
        }
    }
}