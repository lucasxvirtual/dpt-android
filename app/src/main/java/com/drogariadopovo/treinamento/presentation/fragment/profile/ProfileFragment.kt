package com.drogariadopovo.treinamento.presentation.fragment.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.drogariadopovo.treinamento.presentation.activity.main.MainActivity
import com.drogariadopovo.treinamento.R
import com.drogariadopovo.treinamento.databinding.FragmentAwardsBinding
import com.drogariadopovo.treinamento.databinding.FragmentProfileBinding
import com.drogariadopovo.treinamento.presentation.BaseViewModel
import com.drogariadopovo.treinamento.presentation.activity.getintouch.GetInTouchActivity
import com.drogariadopovo.treinamento.presentation.fragment.BaseFragment
import com.drogariadopovo.treinamento.presentation.popup.ProfilePopup
import kotlinx.android.synthetic.main.fragment_profile.view.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class ProfileFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: ProfileViewModel
    lateinit var binding: FragmentProfileBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        screenComponent.inject(this)

        binding.let {
            it.lifecycleOwner = this
            it.viewModel = viewModel
            binding.hrCard.setOnClickListener {
                activity!!.startActivity<GetInTouchActivity>()
            }

            binding.editProfile.setOnClickListener {
                ProfilePopup(activity as MainActivity, viewModel.getUser()) { name, phone -> viewModel.saveUser(name, phone)}
            }
        }

        return binding.root
    }

    override fun getBaseViewModel(): BaseViewModel? {
        return viewModel
    }
}