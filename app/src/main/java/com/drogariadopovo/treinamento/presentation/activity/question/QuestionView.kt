package com.drogariadopovo.treinamento.presentation.activity.question

import android.content.Context
import android.widget.LinearLayout
import com.drogariadopovo.domain.model.Question

abstract class QuestionView {

    abstract fun setAnswer(answers : List<String>)

    abstract fun getAnswer() : String?

    abstract fun setup(question : Question, linearLayout: LinearLayout, context : Context)

}