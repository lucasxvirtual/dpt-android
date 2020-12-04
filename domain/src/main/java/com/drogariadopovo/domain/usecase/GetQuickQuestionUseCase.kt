package com.drogariadopovo.domain.usecase

import com.drogariadopovo.domain.QuestionResult
import com.drogariadopovo.domain.repository.QuizRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetQuickQuestionUseCase @Inject constructor(private val quizRepository: QuizRepository){

    fun execute(): Observable<QuestionResult> {
        return quizRepository.getQuickQuestions()
                .toObservable()
                .map {
                    QuestionResult.Success(it) as QuestionResult
                }
                .onErrorReturn { QuestionResult.Failure(it) }
                .startWith(QuestionResult.Loading)
    }
}