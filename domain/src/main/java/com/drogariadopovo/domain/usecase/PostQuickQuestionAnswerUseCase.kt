package com.drogariadopovo.domain.usecase

import com.drogariadopovo.domain.WorkedResult
import com.drogariadopovo.domain.repository.QuizRepository
import io.reactivex.Observable
import javax.inject.Inject

class PostQuickQuestionAnswerUseCase @Inject constructor(private val quizRepository: QuizRepository) {

    fun execute(id: Int, answer: String) : Observable<WorkedResult> {
        return quizRepository.postQuickQuestionAnswer(id, answer)
                .toObservable()
                .map { WorkedResult.Success(it) as WorkedResult }
                .onErrorReturn { WorkedResult.Failure(it) }
                .startWith(WorkedResult.Loading)
    }

}