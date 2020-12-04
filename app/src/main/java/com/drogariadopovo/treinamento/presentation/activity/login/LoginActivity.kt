package com.drogariadopovo.treinamento.presentation.activity.login

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.drogariadopovo.treinamento.R
import com.drogariadopovo.treinamento.databinding.ActivityLoginBinding
import com.drogariadopovo.treinamento.presentation.BaseViewModel
import com.drogariadopovo.treinamento.presentation.activity.BaseActivity
import javax.inject.Inject

class LoginActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: LoginViewModel

    override fun getBaseViewModel(): BaseViewModel? {
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        screenComponent.inject(this)

        viewModel.bound()

        binding.let {
            it.viewModel = viewModel
            it.lifecycleOwner = this

            setupError(it.cpf, viewModel.getUsernameError())
            setupError(it.password, viewModel.getPasswordError())
        }

        supportActionBar?.hide()

    }
}