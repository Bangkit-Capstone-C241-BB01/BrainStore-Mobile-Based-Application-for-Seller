package com.dicoding.braincoresellerapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.dicoding.braincoresellerapp.data.modal.Result
import com.dicoding.braincoresellerapp.data.response.login.LoginRequest
import com.dicoding.braincoresellerapp.data.response.login.LoginResponse
import com.dicoding.braincoresellerapp.databinding.ActivityLoginBinding
import com.dicoding.braincoresellerapp.ui.MainActivity
import com.dicoding.braincoresellerapp.ui.register.RegisterActivity
import com.dicoding.braincoresellerapp.utils.Preference
import com.dicoding.braincoresellerapp.utils.ViewModelFactory
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels {
        ViewModelFactory(this@LoginActivity)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()

        binding.btnRegister.setOnClickListener{
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

    }



    private fun setupAction(){
        binding.apply {
            btnLogin.setOnClickListener {
                val userName = inputStoreEmail.text.toString()
                val password = inputPassword.text.toString()
                val loginRequest = LoginRequest(
                    user_email = userName, user_password = password
                )
                loginViewModel.login(loginRequest).observe(this@LoginActivity){
                    when (it) {
                        is Result.Loading -> {
                            showLoading(true)
                        }
                        is Result.Success -> {
                            showLoading(false)
                            processLogin(it.data)
                        }
                        is Result.Error -> {
                            showLoading(false)
                            Toast.makeText(this@LoginActivity, it.error, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    private fun processLogin(data: LoginResponse) {
        if (data.msg == "Login Error") {
            Toast.makeText(this, "Login is Error", Toast.LENGTH_LONG).show()
        } else {
            lifecycleScope.launch {
                Preference.saveToken(this@LoginActivity, data.token ?: "")
                Toast.makeText(this@LoginActivity, "Login is Success", Toast.LENGTH_LONG).show()
                navigateToMain()
            }
        }
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.isVisible = state
    }

    private fun navigateToMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}