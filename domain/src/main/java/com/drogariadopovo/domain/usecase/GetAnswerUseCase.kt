package com.drogariadopovo.domain.usecase

import com.drogariadopovo.domain.UserAnswerResult
import com.drogariadopovo.domain.repository.QuizRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetAnswerUseCase @Inject constructor(private val quizRepository: QuizRepository) {

    fun execute(quiz: Int, questionType : String, questionId: Int) : Observable<UserAnswerResult> {
        return quizRepository.getAnswer(quiz, questionType, questionId)
                .toObservable()
                .map { UserAnswerResult.Success(it) as UserAnswerResult }
                .onErrorReturn { UserAnswerResult.Failure(it) }
                .startWith(UserAnswerResult.Loading)
    }

}