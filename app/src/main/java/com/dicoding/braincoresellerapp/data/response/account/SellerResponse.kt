package com.dicoding.braincoresellerapp.data.response.account

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SellerResponse(

	@field:SerializedName("store_id")
	val storeId: Int? = null,

	@field:SerializedName("store_desc")
	val storeDesc: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("user_name")
	val userName: String? = null,

	@field:SerializedName("store_name")
	val storeName: String? = null,

	@field:SerializedName("store_img")
	val storeImg: String? = null,

	@field:SerializedName("store_rate")
	val storeRate: String? = null,

	@field:SerializedName("store_location")
	val storeLocation: String? = null
) : Parcelable
