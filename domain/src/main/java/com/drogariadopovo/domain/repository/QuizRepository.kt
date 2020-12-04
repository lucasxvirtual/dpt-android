package com.drogariadopovo.domain.repository

import com.drogariadopovo.domain.model.*
import io.reactivex.Single

interface QuizRepository {

    fun getQuiz() : Single<QuizPagination>

    fun postAnswer(quiz: Int, questionType : String, questionId: Int, answer: String) : Single<List<UserAnswer>>

    fun getAnswer(quiz: Int, questionType : String, questionId: Int) : Single<List<UserAnswer>>

    fun getRankingBranch() : Single<List<Branch>>

    fun getRanking() : Single<List<User>>

    fun getVoucher() : Single<List<Voucher>>

    fun getQuickQuestions() : Single<Question>

    fun postQuickQuestionAnswer(id : Int, answer: String) : Single<Worked>

    fun getPrize() : Single<List<Prize>>

}