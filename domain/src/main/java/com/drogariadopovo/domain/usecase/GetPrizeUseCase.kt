package com.drogariadopovo.domain.usecase

import com.drogariadopovo.domain.PrizeResult
import com.drogariadopovo.domain.repository.QuizRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetPrizeUseCase @Inject constructor(private val quizRepository: QuizRepository) {

    fun execute() : Observable<PrizeResult> {
        return quizRepository.getPrize()
                .toObservable()
                .map {
                    PrizeResult.Success(it) as PrizeResult
                }
                .onErrorReturn { PrizeResult.Failure(it) }
                .startWith(PrizeResult.Loading)
    }

}