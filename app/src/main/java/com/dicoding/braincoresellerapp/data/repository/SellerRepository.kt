package com.dicoding.braincoresellerapp.data.repository

import android.content.Context
import com.dicoding.braincoresellerapp.utils.Preference

class SellerRepository (private val context: Context) {

    suspend fun logOut() {
        Preference.logOut(context)
    }
}