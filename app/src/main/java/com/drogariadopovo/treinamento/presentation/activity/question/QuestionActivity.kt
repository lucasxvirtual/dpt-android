package com.drogariadopovo.treinamento.presentation.activity.question

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.drogariadopovo.domain.model.Question
import com.drogariadopovo.domain.model.Quiz
import com.drogariadopovo.treinamento.R
import com.drogariadopovo.treinamento.databinding.ActivityQuestionBinding
import com.drogariadopovo.treinamento.ext.loadImage
import com.drogariadopovo.treinamento.presentation.BaseViewModel
import com.drogariadopovo.treinamento.presentation.activity.BaseActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import org.jetbrains.anko.toast
import javax.inject.Inject


class QuestionActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: QuestionViewModel
    lateinit var binding: ActivityQuestionBinding
    lateinit var questionView: QuestionView
    private var alreadyAnswered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_question)

        val quiz = intent.extras!!["quiz"] as Quiz

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = quiz.title

        screenComponent.inject(this)

        binding.let {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }

        val question = intent.extras!!["question"] as Question

        viewModel.bind(quiz, question)
        setup(question)

        viewModel.getAnswer().observe(this, Observer {
            it?.let {list ->
                if(!list.isNullOrEmpty()){
                    alreadyAnswered = true
                    questionView.setAnswer(list.map { answer ->  answer.answer })
                }
            }
        })

        viewModel.getRecreate().observe(this, Observer {
            it?.let {
                recreateActivity(it)
            }
        })

        binding.next.setOnClickListener {
            if(alreadyAnswered){
                viewModel.goNext()
            } else {
                val answer = questionView.getAnswer()
                if (answer != null)
                    viewModel.postAnswer(answer)
                else
                    toast(getString(R.string.invalid_answer))
            }
        }

    }

    private fun setup(question : Question){
        if(!question.image.isNullOrEmpty()){
            val imageView = ImageView(this)
            imageView.loadImage(question.image!!)
            binding.lin.addView(imageView)
        }

        if(!question.video.isNullOrEmpty()){
            val video = YouTubePlayerView(this)
            lifecycle.addObserver(video)
            video.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(question.video!!, 0f)
                }
            })
            binding.lin.addView(video)
        }

        when(question.type){
            "MULTIPLE_CHOICE" -> {
                questionView = QuestionMultipleChoice()
            }
            "SINGLE_CHOICE" -> {
                questionView = QuestionSingleChoice()
            }
            "OPEN_FIELD" -> {
                questionView = QuestionText()
            }
        }

        questionView.setup(question, binding.lin, this)

    }

    override fun getBaseViewModel(): BaseViewModel? {
        return viewModel
    }

    override fun getMessage(number: Int?): String? {
        if(number == 1){
            return getString(R.string.finalized_quiz)
        }
        return super.getMessage(number)
    }

    private fun recreateActivity(question : Question){
        intent.removeExtra("question")
        intent.putExtra("question", question)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}