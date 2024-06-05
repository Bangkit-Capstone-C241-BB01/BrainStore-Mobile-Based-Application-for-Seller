package com.dicoding.braincoresellerapp.data.response.appeal

import com.google.gson.annotations.SerializedName

data class AppealsResponse(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("appeal")
	val appeal: Appeal? = null
)

data class Appeal(

	@field:SerializedName("appeal_desc")
	val appealDesc: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("appeal_id")
	val appealId: Int? = null,

	@field:SerializedName("product_id")
	val productId: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null
)
