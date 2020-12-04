package com.drogariadopovo.treinamento.presentation.popup

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.drogariadopovo.domain.model.Question
import com.drogariadopovo.treinamento.R
import kotlinx.android.synthetic.main.dialog_quick_question.view.*

class QuickQuestionPopup(private val question: Question, private val callback : (String) -> Unit) : DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it, R.style.CustomAlertDialog)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_quick_question, null)

            builder.setView(view)

            view.text.text = question.title

            view.yes.text = question.answers!![0].text
            view.no.text = question.answers!![1].text

            view.yes.setOnClickListener {
                callback.invoke(view.yes.text.toString())
                dismiss()
            }

            view.no.setOnClickListener {
                callback.invoke(view.no.text.toString())
                dismiss()
            }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}