package com.drogariadopovo.domain.usecase

import com.drogariadopovo.domain.QuizResult
import com.drogariadopovo.domain.repository.QuizRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetQuizUseCase @Inject constructor(private val quizRepository: QuizRepository){

    fun execute(): Observable<QuizResult> {
        return quizRepository.getQuiz()
                .toObservable()
                .map {
                    QuizResult.Success(it) as QuizResult
                }
                .onErrorReturn { QuizResult.Failure(it) }
                .startWith(QuizResult.Loading)
    }
}