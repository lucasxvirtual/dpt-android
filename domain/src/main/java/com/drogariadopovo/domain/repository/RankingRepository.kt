package com.drogariadopovo.domain.repository

import com.drogariadopovo.domain.model.Branch
import com.drogariadopovo.domain.model.User
import io.reactivex.Single

interface RankingRepository {

    fun getRanking(): Single<List<User>>

    fun getRankingBranch(): Single<List<Branch>>
}