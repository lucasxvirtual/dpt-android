package com.drogariadopovo.treinamento.presentation.popup

import android.app.AlertDialog
import com.drogariadopovo.domain.model.User
import com.drogariadopovo.treinamento.R
import com.drogariadopovo.treinamento.presentation.activity.BaseActivity
import kotlinx.android.synthetic.main.popup_profile.view.*

class ProfilePopup(val activity: BaseActivity, user : User, callback : (String, String) -> Unit) {

    private var dialog : AlertDialog? = null

    init {
        val builder = AlertDialog.Builder(activity)
        val view =  activity.layoutInflater.inflate(R.layout.popup_profile, null)

        builder.setView( view)
        dialog = builder.create()
        dialog?.show()

        view.name.setText(user.name)
        view.phone.setText(user.phone)
        view.email.setText(user.email)
        view.unity.setText(user.branch!!.name)

        view.save.setOnClickListener {
            val name = view.name.text.toString()
            if(name.isEmpty()){
                view.name.error = activity.getString(R.string.required_field)
                return@setOnClickListener
            }
            callback.invoke(name, view.phone.text.toString())
            dialog!!.dismiss()
        }
    }

}