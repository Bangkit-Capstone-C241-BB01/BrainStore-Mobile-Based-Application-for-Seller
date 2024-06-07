package com.dicoding.braincoresellerapp.ui.register

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.dicoding.braincoresellerapp.data.modal.Result
import com.dicoding.braincoresellerapp.data.response.register.RegisterRequest
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
                                showAlertDialog("Success", "Sign Up is Success, Get login!")
                                navigateToLogin()
                            }
                            is Result.Error -> {
                                showLoading(false)
                                showAlertDialog("Error", "Error Sign Up")
                            }
                        }
                    }
                } else {
                    showAlertDialog("Error", "Passwords do not match")
                }
            }
        }
    }


    private fun showAlertDialog(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
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