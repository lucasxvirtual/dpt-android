package com.drogariadopovo.domain.model

import java.io.Serializable

data class Answer(
        val id : Int,
        val text : String,
        val isRightAnswer : Boolean?
) : Serializable