package com.dicoding.braincoresellerapp.ui.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.dicoding.braincoresellerapp.R
import com.dicoding.braincoresellerapp.ui.welcome.WelcomeActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class CustomSplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_splash_screen)

        val splashScreenDuration = 1000L
        lifecycleScope.launch{
            delay(splashScreenDuration)
            startActivity(Intent(this@CustomSplashScreenActivity, WelcomeActivity::class.java))
            finish()
        }
    }
}