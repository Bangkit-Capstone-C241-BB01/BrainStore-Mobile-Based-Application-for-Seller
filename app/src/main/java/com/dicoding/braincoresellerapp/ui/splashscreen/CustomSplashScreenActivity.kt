package com.dicoding.braincoresellerapp.ui.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dicoding.braincoresellerapp.R
import com.dicoding.braincoresellerapp.ui.MainActivity
import com.dicoding.braincoresellerapp.ui.login.LoginViewModel
import com.dicoding.braincoresellerapp.ui.welcome.WelcomeActivity
import com.dicoding.braincoresellerapp.utils.ViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class CustomSplashScreenActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels {
        ViewModelFactory(this@CustomSplashScreenActivity)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_splash_screen)

        val splashScreenDuration = 1000L
        lifecycleScope.launch{
            delay(splashScreenDuration)

            loginViewModel.getToken().observe(this@CustomSplashScreenActivity) {token ->
                if (token.isNullOrEmpty()){
                    startActivity(Intent(this@CustomSplashScreenActivity, WelcomeActivity::class.java))
                } else {
                    startActivity(Intent(this@CustomSplashScreenActivity, MainActivity::class.java))
                }
                finish()
            }

        }
    }
}