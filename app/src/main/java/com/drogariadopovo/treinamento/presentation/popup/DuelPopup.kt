package com.drogariadopovo.treinamento.presentation.popup

import android.app.AlertDialog
import com.drogariadopovo.treinamento.R
import com.drogariadopovo.treinamento.presentation.activity.BaseActivity

class DuelPopup(val activity: BaseActivity) {

    private var dialog : AlertDialog? = null

    init {
        val builder = AlertDialog.Builder(activity)
        val view =  activity.layoutInflater.inflate(R.layout.popup_duel, null)

        builder.setView( view)
        dialog = builder.create()
        dialog?.show()


    }

}