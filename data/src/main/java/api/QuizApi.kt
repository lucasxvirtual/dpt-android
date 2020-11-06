package api

import io.reactivex.Single
import response.*
import javax.inject.Inject

class QuizApi @Inject constructor(private val quizEndPoint: QuizEndPoint) {

    fun getQuiz() : Single<QuizPaginationResponse> {
        return quizEndPoint.getQuiz()
    }

    fun postAnswer(id: Int, questionType: String, questionId: Int, answer: String) : Single<List<UserAnswerResponse>> {
        return quizEndPoint.postAnswer(id, questionType, questionId, answer)
    }

    fun getAnswer(id: Int, questionType: String, questionId: Int) : Single<List<UserAnswerResponse>> {
        return quizEndPoint.getAnswer(id, questionType, questionId)
    }

    fun getRanking() : Single<List<UserResponse>> {
        return quizEndPoint.getRanking()
    }

    fun getRankingBranch() : Single<List<BranchResponse>> {
        return quizEndPoint.getRankingBranch()
    }

    fun getVoucher() : Single<List<VoucherResponse>> {
        return quizEndPoint.getVoucher()
    }

}