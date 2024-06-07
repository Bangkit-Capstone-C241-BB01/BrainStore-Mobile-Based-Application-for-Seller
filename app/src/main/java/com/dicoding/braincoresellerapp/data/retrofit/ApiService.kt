package com.dicoding.braincoresellerapp.data.retrofit

import com.dicoding.braincoresellerapp.data.response.account.SellerResponse
import com.dicoding.braincoresellerapp.data.response.account.UpdateStoreResponse
import com.dicoding.braincoresellerapp.data.response.appeal.AppealRequest
import com.dicoding.braincoresellerapp.data.response.appeal.AppealsResponse
import com.dicoding.braincoresellerapp.data.response.login.LoginRequest
import com.dicoding.braincoresellerapp.data.response.login.LoginResponse
import com.dicoding.braincoresellerapp.data.response.product.ProductResponse
import com.dicoding.braincoresellerapp.data.response.register.RegisterRequest
import com.dicoding.braincoresellerapp.data.response.register.RegisterResponse
import com.dicoding.braincoresellerapp.data.response.upload.UploadResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part


interface ApiService {

    @POST("register")
    suspend fun register(
        @Body request: RegisterRequest
    ): RegisterResponse

    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @Multipart
    @POST("sellers/products")
    suspend fun upload(
        @Part img_product: MultipartBody.Part,
        @Part("product_name") productName: RequestBody,
        @Part("product_price") productPrice: RequestBody,
        @Part("product_spec") productSpec: RequestBody,
        @Part("product_desc") productDesc: RequestBody,
        @Part("product_stock") productStock: RequestBody,
        @Part("product_category") productCategory: RequestBody
    ):UploadResponse

    @GET("sellers/products")
    suspend fun products(): ProductResponse

    @GET ("sellers/store")
    suspend fun  sellerAccount(): SellerResponse

    @POST("sellers/appeals")
    suspend fun appeals(
        @Body request: AppealRequest
    ): AppealsResponse

    @Multipart
    @PUT("sellers/store")
    suspend fun update(
        @Part img_store: MultipartBody.Part,
        @Part("store_desc") storeDesc: RequestBody,
        @Part("store_location") storeLocation: RequestBody,
    ): UpdateStoreResponse

}

