package com.drogariadopovo.domain.usecase

import com.drogariadopovo.domain.model.User
import com.drogariadopovo.domain.repository.RankingRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetRankingUseCase @Inject constructor(private val rankingRepository: RankingRepository) {

    sealed class Result {
        object Loading : Result()
        data class Success(val users: List<User>) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }

   fun execute() : Observable<Result> {
       return rankingRepository.getRanking()
               .toObservable()
               .map { Result.Success(it) as Result }
               .onErrorReturn { Result.Failure(it) }
               .startWith(Result.Loading)
   }
}