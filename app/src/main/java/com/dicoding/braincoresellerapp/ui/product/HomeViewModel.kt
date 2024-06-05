package com.dicoding.braincoresellerapp.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.braincoresellerapp.data.modal.Result
import com.dicoding.braincoresellerapp.data.repository.SellerRepository
import com.dicoding.braincoresellerapp.data.response.product.ProductResponse

class HomeViewModel(private val sellerRepository: SellerRepository): ViewModel() {
    fun getSellerProduct() : LiveData<Result<ProductResponse>> = sellerRepository.getSellerProduct()
}