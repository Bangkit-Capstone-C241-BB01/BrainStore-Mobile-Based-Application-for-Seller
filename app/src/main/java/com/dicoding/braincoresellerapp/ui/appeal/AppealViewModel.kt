package com.dicoding.braincoresellerapp.ui.appeal

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.braincoresellerapp.data.modal.Result
import com.dicoding.braincoresellerapp.data.repository.SellerRepository
import com.dicoding.braincoresellerapp.data.response.appeal.AppealRequest
import com.dicoding.braincoresellerapp.data.response.appeal.AppealsResponse

class AppealViewModel(private val sellerRepository: SellerRepository) : ViewModel() {
    fun appeal(appealRequest: AppealRequest): LiveData<Result<AppealsResponse>> {
        return sellerRepository.postAppeal(appealRequest)
    }
}