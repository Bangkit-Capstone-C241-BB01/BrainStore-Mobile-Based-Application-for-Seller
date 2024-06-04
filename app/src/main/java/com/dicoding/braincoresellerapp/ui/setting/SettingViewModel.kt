package com.dicoding.braincoresellerapp.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.braincoresellerapp.data.repository.SellerRepository
import kotlinx.coroutines.launch

class SettingViewModel(private val repository: SellerRepository): ViewModel() {
    fun logout(){
        viewModelScope.launch {
            repository.logOut()
        }
    }
}