package com.drogariadopovo.treinamento.presentation.activity.question

import android.content.Context
import android.widget.CheckBox
import android.widget.LinearLayout
import com.drogariadopovo.domain.model.Question
import org.json.JSONArray

class QuestionMultipleChoice : QuestionView() {

    private var checkBoxList = arrayListOf<CheckBox>()

    override fun setAnswer(answers: List<String>) {
        checkBoxList.forEach {
            it.isEnabled = false
            if(answers.contains(it.text)){
                it.isChecked = true
            }
        }
    }

    override fun getAnswer(): String? {
        val jsonArray = JSONArray()
        checkBoxList.forEach {
            if(it.isChecked){
                jsonArray.put(it.text)
            }
        }
        return jsonArray.toString()
    }

    override fun setup(question: Question, linearLayout: LinearLayout, context : Context) {
        question.answers?.forEach {
            val checkBox = CheckBox(context)
            checkBox.text = it.text
            checkBoxList.add(checkBox)
            linearLayout.addView(checkBox)
        }
    }
}