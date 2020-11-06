package com.drogariadopovo.treinamento.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.drogariadopovo.treinamento.presentation.activity.main.MainActivity
import com.drogariadopovo.treinamento.R
import com.drogariadopovo.treinamento.presentation.activity.getintouch.GetInTouchActivity
import com.drogariadopovo.treinamento.presentation.popup.ProfilePopup
import kotlinx.android.synthetic.main.fragment_profile.view.*
import org.jetbrains.anko.startActivity

class ProfileFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)


        view.hr_card.setOnClickListener {
            activity!!.startActivity<GetInTouchActivity>()
        }

        view.edit_profile.setOnClickListener {
            ProfilePopup(activity as MainActivity)
        }

        return view
    }
}