package com.drogariadopovo.treinamento.presentation.activity.quiz

import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import com.drogariadopovo.domain.model.Question
import com.drogariadopovo.domain.model.Quiz
import com.drogariadopovo.treinamento.BaseApplication
import com.drogariadopovo.treinamento.presentation.BaseViewModel
import javax.inject.Inject

class QuizViewModel @Inject constructor(
        application: BaseApplication,
        private val router: QuizRouter
) : BaseViewModel(application) {

    val quiz = MutableLiveData<Quiz?>().apply { value = null }
    val questions = MutableLiveData<List<Question>>().apply { value = emptyList() }

    fun bind(quiz : Quiz){
        this.quiz.postValue(quiz)
        questions.postValue(quiz.questions)
    }

    override fun onClickItem(item: Any) {
        val question = item as Question
        router.navigate(QuizRouter.Route.QUESTION, bundleOf("question" to question, "quiz" to quiz.value!!))
    }

}