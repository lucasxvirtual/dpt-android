package com.drogariadopovo.treinamento.presentation.activity.question

import android.content.Context
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import com.drogariadopovo.domain.model.Question

class QuestionSingleChoice : QuestionView() {

    private lateinit var radioGroup: RadioGroup
    private var radioButtons = arrayListOf<RadioButton>()

    override fun setAnswer(answers: List<String>) {
        radioButtons.forEach {
            it.isEnabled = false
            if(answers.contains(it.text)){
                it.isChecked = true
            }
        }
    }

    override fun getAnswer(): String? {
        radioButtons.forEach {
            if(it.isChecked){
                return it.text.toString()
            }
        }
        return null
    }

    override fun setup(question: Question, linearLayout: LinearLayout, context : Context) {
        radioGroup = RadioGroup(context)
        question.answers?.forEach {
            val radioButton = RadioButton(context)
            radioButton.text = it.text
            radioButtons.add(radioButton)
            radioGroup.addView(radioButton)
        }
        linearLayout.addView(radioGroup)
    }
}