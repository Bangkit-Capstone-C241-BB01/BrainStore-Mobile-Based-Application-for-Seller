package com.dicoding.braincoresellerapp.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.dicoding.braincoresellerapp.data.modal.Result
import com.dicoding.braincoresellerapp.data.response.register.RegisterRequest
import com.dicoding.braincoresellerapp.data.response.register.RegisterResponse
import com.dicoding.braincoresellerapp.databinding.ActivityRegisterBinding
import com.dicoding.braincoresellerapp.ui.login.LoginActivity
import com.dicoding.braincoresellerapp.utils.ViewModelFactory

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModels {
        ViewModelFactory(this@RegisterActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()

        binding.btnLogin.setOnClickListener{
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
    }

    private fun setupAction() {
        binding.apply {
            btnRegister.setOnClickListener{
                val userName = inputSellerName.text.toString()
                val storeName = inputStoreName.text.toString()
                val email = inputStoreEmail.text.toString()
                val password = inputPassword.text.toString()
                val confirmPassword = confirmPassword.text.toString()

                if (password == confirmPassword) {
                    val registerRequest = RegisterRequest(
                        user_name = userName,
                        user_email = email,
                        user_password = password,
                        user_role = "seller",
                        store_name = storeName
                    )

                    registerViewModel.register(registerRequest).observe(this@RegisterActivity){
                        when (it) {
                            is Result.Loading -> {
                                showLoading(true)
                            }
                            is Result.Success -> {
                                showLoading(false)
                                processRegister(it.data)
                            }
                            is Result.Error -> {
                                showLoading(false)
                                Toast.makeText(this@RegisterActivity, it.error, Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                } else {
                    Toast.makeText(this@RegisterActivity, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun processRegister(data: RegisterResponse) {
        if (data.msg == "Error Sign Up") {
            Toast.makeText(this, "Error Sign Up", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Sign Up is Success, Get login!", Toast.LENGTH_LONG).show()
            navigateToLogin()
        }
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.isVisible = state
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}