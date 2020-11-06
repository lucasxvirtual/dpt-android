package com.drogariadopovo.domain.model

data class QuizPagination(
        val count : Int,
        val next : String?,
        val previous: String?,
        val results : List<Quiz>
)