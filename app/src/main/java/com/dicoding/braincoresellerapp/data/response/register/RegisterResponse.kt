package com.dicoding.braincoresellerapp.data.response.register

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)
