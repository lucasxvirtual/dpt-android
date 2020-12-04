package com.drogariadopovo.domain

import com.drogariadopovo.domain.model.*

sealed class UserResult {
    object Loading : UserResult()
    data class Success(val user: User) : UserResult()
    data class Failure(val throwable: Throwable) : UserResult()
}

sealed class WorkedResult {
    object Loading : WorkedResult()
    data class Success(val worked: Worked) : WorkedResult()
    data class Failure(val throwable: Throwable) : WorkedResult()
}

sealed class QuizResult {
    object Loading : QuizResult()
    data class Success(val quizPagination: QuizPagination) : QuizResult()
    data class Failure(val throwable: Throwable) : QuizResult()
}

sealed class UserAnswerResult {
    object Loading : UserAnswerResult()
    data class Success(val userAnswers: List<UserAnswer>) : UserAnswerResult()
    data class Failure(val throwable: Throwable) : UserAnswerResult()
}

sealed class QuestionResult {
    object Loading : QuestionResult()
    data class Success(val question: Question) : QuestionResult()
    data class Failure(val throwable: Throwable) : QuestionResult()
}

sealed class EmptyResult {
    object Loading : EmptyResult()
    data class Success(val unit: Int) : EmptyResult()
    data class Failure(val throwable: Throwable) : EmptyResult()
}

sealed class PrizeResult {
    object Loading : PrizeResult()
    data class Success(val prizes: List<Prize>) : PrizeResult()
    data class Failure(val throwable: Throwable) : PrizeResult()
}
