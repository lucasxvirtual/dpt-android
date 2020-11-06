package com.drogariadopovo.treinamento.presentation.fragment.ranking

import android.os.Bundle
import androidx.core.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.drogariadopovo.treinamento.R
import com.drogariadopovo.treinamento.databinding.FragmentRankingBinding
import com.drogariadopovo.treinamento.presentation.adapter.RankingAdapter
import com.drogariadopovo.treinamento.presentation.fragment.BaseFragment
import javax.inject.Inject

class RankingFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: RankingViewModel

    lateinit var binding: FragmentRankingBinding

    lateinit var adapter: RankingAdapter

    override fun getBaseViewModel() = viewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRankingBinding.inflate(inflater, container, false)

        screenComponent.inject(this)

        binding.let {
            it.viewModel = viewModel
            it.lifecycleOwner = this
            viewModel.bound()
        }

        adapter = RankingAdapter(emptyList())
        binding.recyclerStoreNet.layoutManager = LinearLayoutManager(context)
        binding.recyclerStoreNet.adapter = adapter

        viewModel.getIsStore().observe(viewLifecycleOwner, Observer {
            if (it){
                onStoreClicked()
            } else {
                onNetClicked()
            }
        })

        viewModel.getUsers().observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.apply {
                    onItemClickListener = {  }
                    userToRanking(viewModel.getUsers().value!!, viewModel.getCurrentUser().value!!)
                }
            }
        })


        return binding.root
    }

    private fun onStoreClicked(){
        binding.storeText.setTextColor(ContextCompat.getColor(view!!.context,R.color.white))
        binding.store.backgroundTintList = ContextCompat.getColorStateList(view!!.context, R.color.blue)

        binding.netText.setTextColor(ContextCompat.getColor(view!!.context,R.color.blue))
        binding.net.backgroundTintList = ContextCompat.getColorStateList(view!!.context, R.color.white)

        adapter.apply {
            onItemClickListener = {  }
            userToRanking(viewModel.getUsers().value!!, viewModel.getCurrentUser().value!!)
        }
    }

    private fun onNetClicked(){
        binding.storeText.setTextColor(ContextCompat.getColor(view!!.context, R.color.blue))
        binding.store.backgroundTintList = ContextCompat.getColorStateList(view!!.context, R.color.white)

        binding.netText.setTextColor(ContextCompat.getColor(view!!.context, R.color.white))
        binding.net.backgroundTintList = ContextCompat.getColorStateList(view!!.context, R.color.blue)

        adapter.apply {
            onItemClickListener = {  }
            branchToRanking(viewModel.getBranches().value!!, viewModel.getCurrentUser().value!!)
        }
    }

}