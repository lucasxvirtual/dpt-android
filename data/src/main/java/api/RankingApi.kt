package api

import io.reactivex.Single
import response.BranchResponse
import response.UserResponse
import javax.inject.Inject

class RankingApi @Inject constructor(private val rankingEndPoint: RankingEndPoint) {

    fun getRanking(): Single<List<UserResponse>>{
        return rankingEndPoint.getRanking()
    }

    fun getRankingBranch() : Single<List<BranchResponse>>{
        return rankingEndPoint.getRankingBranch()
    }
}