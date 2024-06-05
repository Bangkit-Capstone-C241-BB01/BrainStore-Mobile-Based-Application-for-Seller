package com.dicoding.braincoresellerapp.data.response.register

data class RegisterRequest(
    val user_name: String,
    val user_email: String,
    val user_password: String,
    val user_role: String,
    val store_name: String
)
