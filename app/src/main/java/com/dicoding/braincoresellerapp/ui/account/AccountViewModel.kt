package com.dicoding.braincoresellerapp.ui.account

import androidx.lifecycle.ViewModel
import com.dicoding.braincoresellerapp.data.repository.SellerRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AccountViewModel(private val sellerRepository: SellerRepository): ViewModel() {

    fun UpdateStoreData(
        imgStore: MultipartBody.Part,
        storeDesc: RequestBody,
        storeLocation: RequestBody
    ) = sellerRepository.updateStoreData(imgStore, storeDesc, storeLocation)
}