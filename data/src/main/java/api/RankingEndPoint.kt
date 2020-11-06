package api

import io.reactivex.Single
import response.BranchResponse
import response.UserResponse
import retrofit2.http.GET

interface RankingEndPoint {

    @GET("core/ranking/")
    fun getRanking(): Single<List<UserResponse>>

    @GET("core/ranking-branch/")
    fun getRankingBranch() : Single<List<BranchResponse>>
}