package com.drogariadopovo.treinamento.presentation.popup

import android.app.AlertDialog
import com.drogariadopovo.treinamento.R
import com.drogariadopovo.treinamento.presentation.activity.BaseActivity
import kotlinx.android.synthetic.main.popup_profile.view.*

class ProfilePopup(val activity: BaseActivity) {

    private var dialog : AlertDialog? = null

    init {
        val builder = AlertDialog.Builder(activity)
        val view =  activity.layoutInflater.inflate(R.layout.popup_profile, null)

        builder.setView( view)
        dialog = builder.create()
        dialog?.show()

        view.save.setOnClickListener {
            dialog!!.dismiss()
        }
    }

}