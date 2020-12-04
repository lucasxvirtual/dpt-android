package com.drogariadopovo.treinamento.presentation.activity.quiz

import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.drogariadopovo.domain.model.Quiz
import com.drogariadopovo.treinamento.R
import com.drogariadopovo.treinamento.databinding.ActivityQuizBinding
import com.drogariadopovo.treinamento.presentation.BaseViewModel
import com.drogariadopovo.treinamento.presentation.activity.BaseActivity
import com.drogariadopovo.treinamento.presentation.adapter.QuizAdapter
import javax.inject.Inject

class QuizActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: QuizViewModel

    override fun getBaseViewModel(): BaseViewModel? {
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityQuizBinding = DataBindingUtil.setContentView(this, R.layout.activity_quiz)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        screenComponent.inject(this)

        viewModel.bind(intent.extras!!["quiz"] as Quiz)

        binding.let {
            it.lifecycleOwner = this
            it.viewModel = viewModel

            it.recycler.layoutManager = LinearLayoutManager(this)
            it.recycler.adapter = QuizAdapter(emptyList()).apply {
                onButtonClickListener = { viewModel.onClickItem(it) }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

}
