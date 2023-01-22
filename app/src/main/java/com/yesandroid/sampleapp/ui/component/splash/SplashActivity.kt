package com.yesandroid.sampleapp.ui.component.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import com.yesandroid.sampleapp.databinding.SplashLayoutBinding
import com.yesandroid.sampleapp.ui.base.BaseActivity
import com.yesandroid.sampleapp.ui.component.login.LoginActivity
import com.yesandroid.sampleapp.SPLASH_DELAY
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Purushottam
 */
@AndroidEntryPoint
class SplashActivity : BaseActivity(){

    private lateinit var binding: SplashLayoutBinding

    override fun initViewBinding() {
        binding = SplashLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToMainScreen()
    }

    override fun observeViewModel() {
    }

    private fun navigateToMainScreen() {
        Handler().postDelayed({
            val nextScreenIntent = Intent(this, LoginActivity::class.java)
            startActivity(nextScreenIntent)
            finish()
        }, SPLASH_DELAY.toLong())
    }
}
