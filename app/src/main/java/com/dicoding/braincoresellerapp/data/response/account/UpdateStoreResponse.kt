package com.dicoding.braincoresellerapp.data.response.account

import com.google.gson.annotations.SerializedName

data class UpdateStoreResponse(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("store")
	val store: Store? = null
)

data class Store(

	@field:SerializedName("store_id")
	val storeId: Int? = null,

	@field:SerializedName("store_desc")
	val storeDesc: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("store_name")
	val storeName: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("userUserId")
	val userUserId: Any? = null,

	@field:SerializedName("store_img")
	val storeImg: String? = null,

	@field:SerializedName("store_rate")
	val storeRate: Int? = null,

	@field:SerializedName("store_location")
	val storeLocation: String? = null
)
