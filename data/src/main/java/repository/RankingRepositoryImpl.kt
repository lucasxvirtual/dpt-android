package repository

import api.RankingApi
import com.drogariadopovo.domain.model.Branch
import com.drogariadopovo.domain.model.User
import com.drogariadopovo.domain.repository.RankingRepository
import io.reactivex.Single
import mapper.BranchMapper
import mapper.UserMapper
import javax.inject.Inject

class RankingRepositoryImpl @Inject constructor(
        private val rankingApi: RankingApi,
        private val branchMapper: BranchMapper,
        private val userMapper: UserMapper) : RankingRepository {

    override fun getRanking(): Single<List<User>> {
        return rankingApi.getRanking().map { userMapper.map(it) }
    }

    override fun getRankingBranch(): Single<List<Branch>> {
        return rankingApi.getRankingBranch().map { branchMapper.map(it) }
    }
}