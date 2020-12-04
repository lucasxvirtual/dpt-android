package api

import io.reactivex.Single
import response.*
import retrofit2.http.*

interface QuizEndPoint {

    @GET("core/quiz/")
    fun getQuiz() : Single<QuizPaginationResponse>

    @POST("core/quiz/{id}/answer-question/")
    @FormUrlEncoded
    fun postAnswer(@Path("id") id: Int,
                   @Field("question_type") questionType: String,
                   @Field("question_id") questionId: Int,
                   @Field("answer") answer: String
                   ) : Single<List<UserAnswerResponse>>

    @GET("core/quiz/{id}/answer-question/")
    fun getAnswer(@Path("id") id: Int,
                  @Query("question_type") questionType: String,
                  @Query("question_id") questionId: Int) : Single<List<UserAnswerResponse>>

    @GET("core/ranking/")
    fun getRanking() : Single<List<UserResponse>>

    @GET("core/ranking-branch/")
    fun getRankingBranch() : Single<List<BranchResponse>>

    @GET("core/voucher/")
    fun getVoucher() : Single<List<VoucherResponse>>

    @GET("core/quick-question/")
    fun getQuickQuestion() : Single<QuickQuestionResponse>

    @FormUrlEncoded
    @POST("core/answer-quick-question/")
    fun postQuickQuestionAnswer(
            @Field("quick_question") id : Int,
            @Field("answer") answer: String
    ) : Single<WorkedResponse>

    @GET("core/prize-user/")
    fun getPrizeUser() : Single<List<PrizeResponse>>

}