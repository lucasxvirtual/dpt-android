package repository

import api.QuizApi
import com.drogariadopovo.domain.model.*
import com.drogariadopovo.domain.repository.QuizRepository
import io.reactivex.Single
import mapper.*
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
        private val quizApi: QuizApi,
        private val quizPaginationMapper: QuizPaginationMapper,
        private val userAnswerMapper: UserAnswerMapper,
        private val userMapper: UserMapper,
        private val branchMapper: BranchMapper,
        private val voucherMapper: VoucherMapper
) : QuizRepository {

    override fun getQuiz(): Single<QuizPagination> {
        return quizApi.getQuiz().map { quizPaginationMapper.map(it) }
    }

    override fun postAnswer(quiz: Int, questionType: String, questionId: Int, answer: String): Single<List<UserAnswer>> {
        return quizApi.postAnswer(quiz, questionType, questionId, answer).map { userAnswerMapper.map(it) }
    }

    override fun getAnswer(quiz: Int, questionType: String, questionId: Int): Single<List<UserAnswer>> {
        return quizApi.getAnswer(quiz, questionType, questionId).map { userAnswerMapper.map(it) }
    }

    override fun getRankingBranch(): Single<List<Branch>> {
        return quizApi.getRankingBranch().map { branchMapper.map(it) }
    }

    override fun getRanking(): Single<List<User>> {
        return quizApi.getRanking().map { userMapper.map(it) }
    }

    override fun getVoucher(): Single<List<Voucher>> {
        return quizApi.getVoucher().map { voucherMapper.map(it) }
    }

//    override fun getQuickQuestions(): Single<List<Question>> {
//        return quizApi.getQuiz().map { quizPaginationMapper.map(it) }
//    }

}