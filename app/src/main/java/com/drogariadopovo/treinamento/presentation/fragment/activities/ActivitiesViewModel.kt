package com.drogariadopovo.treinamento.presentation.fragment.activities

import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import com.drogariadopovo.domain.QuizResult
import com.drogariadopovo.domain.model.Quiz
import com.drogariadopovo.domain.usecase.GetQuizUseCase
import com.drogariadopovo.treinamento.BaseApplication
import com.drogariadopovo.treinamento.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ActivitiesViewModel @Inject constructor(private val quizUseCase: GetQuizUseCase,
                                              private val router: ActivitiesRouter,
                                              application: BaseApplication) : BaseViewModel(application) {

    val quiz = MutableLiveData<List<Quiz>>().apply { value = listOf() }
    val nonBlockingLoading = MutableLiveData<Boolean>().apply { value = false }

    fun bound(){
        quizUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {handleQuizResult(it)}
                .addTo(disposables)
    }

    override fun onClickItem(item: Any) {
        val quiz = item as Quiz
        val bundle = bundleOf("quiz" to quiz)
        router.navigate(ActivitiesRouter.Route.QUIZ, bundle)
    }

    private fun handleQuizResult(result: QuizResult){
        nonBlockingLoading.postValue(result is QuizResult.Loading)
        when(result){
            is QuizResult.Success -> {
                quiz.postValue(result.quizPagination.results)
            }
            is QuizResult.Failure -> {
                setToast(-1)
            }
        }
    }

}