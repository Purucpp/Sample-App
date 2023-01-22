package com.yesandroid.sampleapp.ui.component.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar
import com.yesandroid.sampleapp.data.Resource
import com.yesandroid.sampleapp.data.dto.login.LoginResponse
import com.yesandroid.sampleapp.databinding.LoginActivityBinding
import com.yesandroid.sampleapp.ui.base.BaseActivity
import com.yesandroid.sampleapp.ui.component.recipes.RecipesListActivity
import com.yesandroid.sampleapp.utils.*
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Purushottam
 */
@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: LoginActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.login.setOnClickListener { doLogin() }
    }

    override fun initViewBinding() {
        binding = LoginActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun observeViewModel() {
        observe(loginViewModel.loginLiveData, ::handleLoginResult)
        observeSnackBarMessages(loginViewModel.showSnackBar)
        observeToast(loginViewModel.showToast)
    }

    private fun doLogin() {
        loginViewModel.doLogin(
            binding.username.text.trim().toString(),
            binding.password.text.toString()
        )
    }

    private fun handleLoginResult(status: Resource<LoginResponse>) {
        when (status) {
            is Resource.Loading -> binding.loaderView.toVisible()
            is Resource.Success -> status.data?.let {
                binding.loaderView.toGone()
                navigateToMainScreen()
            }
            is Resource.DataError -> {
                binding.loaderView.toGone()
                status.errorCode?.let { loginViewModel.showToastMessage(it) }
            }
        }
    }

    private fun navigateToMainScreen() {
        val nextScreenIntent = Intent(this, RecipesListActivity::class.java)
        startActivity(nextScreenIntent)
        finish()
    }

    private fun observeSnackBarMessages(event: LiveData<SingleEvent<Any>>) {
        binding.root.setupSnackbar(this, event, Snackbar.LENGTH_LONG)
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }
}