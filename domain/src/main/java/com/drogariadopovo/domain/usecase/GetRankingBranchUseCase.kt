package com.drogariadopovo.domain.usecase

import com.drogariadopovo.domain.model.Branch
import com.drogariadopovo.domain.model.User
import com.drogariadopovo.domain.repository.RankingRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetRankingBranchUseCase @Inject constructor(private val rankingRepository: RankingRepository) {

    sealed class Result {
        object Loading : Result()
        data class Success(val branches: List<Branch>) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }

   fun execute() : Observable<Result> {
       return rankingRepository.getRankingBranch()
               .toObservable()
               .map { Result.Success(it) as Result }
               .onErrorReturn { Result.Failure(it) }
               .startWith(Result.Loading)
   }
}