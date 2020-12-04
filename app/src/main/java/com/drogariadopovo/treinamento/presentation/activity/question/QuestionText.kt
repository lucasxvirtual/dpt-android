package com.drogariadopovo.treinamento.presentation.activity.question

import android.content.Context
import android.view.Gravity
import android.widget.EditText
import android.widget.LinearLayout
import com.drogariadopovo.domain.model.Question
import com.drogariadopovo.treinamento.R

class QuestionText  : QuestionView() {

    private lateinit var editText : EditText

    override fun setAnswer(answers: List<String>) {
        answers.forEach {
            editText.setText(it)
        }

        editText.isEnabled = false
    }

    override fun getAnswer(): String? {
        return editText.text.toString()
    }

    override fun setup(question: Question, linearLayout: LinearLayout, context : Context) {
        editText = EditText(context)
        editText.setBackgroundResource(R.drawable.answer_edit_text_border)
        editText.setPadding(10, 0, 10, 0)
        editText.setLines(3)
        editText.gravity = Gravity.TOP
        linearLayout.addView(editText)
    }
}