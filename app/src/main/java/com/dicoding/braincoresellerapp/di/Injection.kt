package com.dicoding.braincoresellerapp.di

import android.content.Context
import com.dicoding.braincoresellerapp.data.repository.SellerRepository

object Injection {

    fun provideRepository(context: Context) : SellerRepository{
        return SellerRepository(context)
    }
}