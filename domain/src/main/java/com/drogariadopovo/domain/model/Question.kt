package com.drogariadopovo.domain.model

import java.io.Serializable

data class Question(
        val id : Int,
        val answers : List<Answer>?,
        val type : String,
        val title : String,
        val description : String?,
        val image : String?,
        val video : String?
) : Serializable