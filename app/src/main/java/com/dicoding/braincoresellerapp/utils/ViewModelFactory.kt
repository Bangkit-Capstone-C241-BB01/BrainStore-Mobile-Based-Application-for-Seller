package com.dicoding.braincoresellerapp.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.braincoresellerapp.di.Injection
import com.dicoding.braincoresellerapp.ui.appeal.AppealViewModel
import com.dicoding.braincoresellerapp.ui.login.LoginViewModel
import com.dicoding.braincoresellerapp.ui.product.HomeViewModel
import com.dicoding.braincoresellerapp.ui.register.RegisterViewModel
import com.dicoding.braincoresellerapp.ui.setting.SettingViewModel
import com.dicoding.braincoresellerapp.ui.upload.UploadViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory (private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(SettingViewModel::class.java) -> {
                SettingViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(UploadViewModel::class.java) -> {
                UploadViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(AppealViewModel::class.java) -> {
                AppealViewModel(Injection.provideRepository(context)) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}