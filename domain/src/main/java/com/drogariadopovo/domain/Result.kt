package com.drogariadopovo.domain

import com.drogariadopovo.domain.model.QuizPagination
import com.drogariadopovo.domain.model.User
import com.drogariadopovo.domain.model.Worked

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