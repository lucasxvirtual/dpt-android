package com.drogariadopovo.treinamento.presentation.activity.getintouch

import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.drogariadopovo.treinamento.R
import com.drogariadopovo.treinamento.databinding.ActivityGetInTouchBinding
import com.drogariadopovo.treinamento.presentation.BaseViewModel
import com.drogariadopovo.treinamento.presentation.activity.BaseActivity
import javax.inject.Inject

class GetInTouchActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: GetInTouchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityGetInTouchBinding = DataBindingUtil.setContentView(this, R.layout.activity_get_in_touch)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.let {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun getBaseViewModel(): BaseViewModel? {
        return viewModel
    }

    override fun getMessage(number: Int?): String? {
        return when(number) {
            1 -> "Contato enviado com sucesso!"
            2 -> "Erro ao enviar contato!"
            else -> super.getMessage(number)
        }
    }

}