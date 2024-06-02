package com.dicoding.braincoresellerapp.ui.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.braincoresellerapp.databinding.ActivityWelcomeBinding
import com.dicoding.braincoresellerapp.ui.login.LoginActivity
import com.dicoding.braincoresellerapp.ui.register.RegisterActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnLogin.setOnClickListener{
            startActivity(Intent(this@WelcomeActivity, LoginActivity::class.java))
        }

        binding.btnRegister.setOnClickListener{
            startActivity(Intent(this@WelcomeActivity, RegisterActivity::class.java))
        }
    }
}