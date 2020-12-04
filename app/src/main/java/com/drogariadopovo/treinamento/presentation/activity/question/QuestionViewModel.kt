package com.drogariadopovo.treinamento.presentation.activity.question

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.drogariadopovo.domain.UserAnswerResult
import com.drogariadopovo.domain.model.Question
import com.drogariadopovo.domain.model.Quiz
import com.drogariadopovo.domain.model.UserAnswer
import com.drogariadopovo.domain.usecase.GetAnswerUseCase
import com.drogariadopovo.domain.usecase.PostAnswerUseCase
import com.drogariadopovo.treinamento.BaseApplication
import com.drogariadopovo.treinamento.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class QuestionViewModel @Inject constructor(
        application: BaseApplication,
        private val router: QuestionRouter,
        private val postAnswerUseCase: PostAnswerUseCase,
        private val getAnswerUseCase: GetAnswerUseCase
) : BaseViewModel(application) {

    private lateinit var quiz: Quiz

    val question = MutableLiveData<Question>().apply { value = null }
    private val recreate = MutableLiveData<Question>().apply { value = null }
    private val answer = MutableLiveData<List<UserAnswer>>().apply { value = null }

    fun getAnswer() : LiveData<List<UserAnswer>> = answer

    fun getRecreate() : LiveData<Question> = recreate

    fun bind(quiz : Quiz, question : Question) {
        this.question.postValue(question)
        this.quiz = quiz
        getAnswerUseCase.execute(quiz.id, question.type, question.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {handleResultGetAnswer(it)}
                .addTo(disposables)
    }

    fun postAnswer(string : String) {
        val question = this.question.value!!
        postAnswerUseCase.execute(quiz.id, question.type, question.id, string)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {handleResult(it)}
                .addTo(disposables)
    }

    private fun handleResult(result: UserAnswerResult) {
        when (result){
            is UserAnswerResult.Success -> {
                goNext()
                hideDialog()
            }
            is UserAnswerResult.Failure -> {
                hideDialog()
            }
            is UserAnswerResult.Loading -> {
                showDialog()
            }
        }
    }

    fun goNext(){
        val question = this.question.value!!

        if(question.id == quiz.questions.last().id && question.type == quiz.questions.last().type) {
            setToast(1)
            router.goBack()
        } else {
            recreate.postValue(quiz.questions[quiz.questions.indexOf(question) + 1])
        }
    }

    private fun handleResultGetAnswer(result: UserAnswerResult) {
        when (result){
            is UserAnswerResult.Success -> {
                answer.postValue(result.userAnswers)
                hideDialog()
            }
            is UserAnswerResult.Failure -> {
                hideDialog()
            }
            is UserAnswerResult.Loading -> {
                showDialog()
            }
        }
    }

}