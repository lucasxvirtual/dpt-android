package com.drogariadopovo.domain.model

import java.io.Serializable

data class Quiz(
        val id: Int,
        val questions : List<Question>,
        val percent : Float = 0f,
        val title : String,
        val description : String?,
        val dueDate : String?
) : Serializable {

    fun getFormattedPercent() = String.format("%d%%", (percent.times(100)).toInt())

}