package com.drogariadopovo.domain.model

import java.io.Serializable

data class UserAnswer(
        val id : Int,
        val answer : String,
        val correct : Boolean?,
        val question : Int
) : Serializable