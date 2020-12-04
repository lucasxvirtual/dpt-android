package com.drogariadopovo.treinamento.presentation.activity.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.drogariadopovo.domain.QuestionResult
import com.drogariadopovo.domain.model.Question
import com.drogariadopovo.domain.usecase.GetQuickQuestionUseCase
import com.drogariadopovo.domain.usecase.PostQuickQuestionAnswerUseCase
import com.drogariadopovo.treinamento.BaseApplication
import com.drogariadopovo.treinamento.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(application: BaseApplication,
                                        private val getQuickQuestionUseCase: GetQuickQuestionUseCase,
                                        private val postQuickQuestionAnswerUseCase: PostQuickQuestionAnswerUseCase
) : BaseViewModel(application) {

    private val question = MutableLiveData<Question?>().apply { value = null }

    fun getQuestion() : LiveData<Question?> = question

    fun bound(){
        getQuickQuestionUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {handleResult(it)}
                .addTo(disposables)
    }

    private fun handleResult(result: QuestionResult){
        if(result is QuestionResult.Success) {
            question.postValue(result.question)
        }
    }

    fun postAnswer(id : Int, answer : String) {
        postQuickQuestionAnswerUseCase.execute(id, answer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { Log.i("ANSWER", it.toString()) }
                .addTo(disposables)
    }

}