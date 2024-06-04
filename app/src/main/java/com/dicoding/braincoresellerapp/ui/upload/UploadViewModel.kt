package com.dicoding.braincoresellerapp.ui.upload

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.braincoresellerapp.data.modal.Result
import com.dicoding.braincoresellerapp.data.repository.SellerRepository
import com.dicoding.braincoresellerapp.data.response.upload.UploadResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

class UploadViewModel(private val sellerRepository: SellerRepository): ViewModel() {
    fun uploadProduct(
        imgProduct: MultipartBody.Part,
        productName: RequestBody,
        productPrice: RequestBody,
        productSpec: RequestBody,
        productDesc: RequestBody,
        productStock: RequestBody,
        productCategory: RequestBody
    ): LiveData<Result<UploadResponse>> = sellerRepository.uploadProduct(
        imgProduct, productName, productPrice, productSpec, productDesc, productStock, productCategory
    )
}