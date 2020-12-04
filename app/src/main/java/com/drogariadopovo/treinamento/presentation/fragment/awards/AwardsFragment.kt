package com.drogariadopovo.treinamento.presentation.fragment.awards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.drogariadopovo.treinamento.R
import com.drogariadopovo.treinamento.databinding.FragmentAwardsBinding
import com.drogariadopovo.treinamento.presentation.adapter.PrizeAdapter
import com.drogariadopovo.treinamento.presentation.adapter.VoucherAdapter
import com.drogariadopovo.treinamento.presentation.fragment.BaseFragment
import javax.inject.Inject

class AwardsFragment : BaseFragment() {

    @Inject
    lateinit var viewModel : AwardsViewModel

    lateinit var binding: FragmentAwardsBinding

    private val prizeAdapter = PrizeAdapter(emptyList())
    private val voucherAdapter = VoucherAdapter(emptyList(), this).apply {
        onButtonClickListener = {}
    }

    override fun getBaseViewModel() = viewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAwardsBinding.inflate(inflater, container, false)

        screenComponent.inject(this)

        binding.let {
            it.viewModel = viewModel
            it.lifecycleOwner = this
            viewModel.bound()
        }

        viewModel.getVouchers().observe(viewLifecycleOwner, Observer {
            it?.let {
                voucherAdapter.replaceItems(it)
            }
        })

        viewModel.getPrizes().observe(viewLifecycleOwner, Observer {
            it?.let {
                prizeAdapter.replaceItems(it)
            }
        })

        viewModel.getIsPrizeClicked().observe(viewLifecycleOwner, Observer {
            if (it){
                onPrizeClicked()
            } else {
                onVoucherClicked()
            }
        })


        return binding.root
    }

    private fun onPrizeClicked(){
        binding.prizeText.setTextColor(ContextCompat.getColor(view!!.context,R.color.white))
        binding.prize.backgroundTintList = ContextCompat.getColorStateList(view!!.context, R.color.blue)

        binding.vouchersText.setTextColor(ContextCompat.getColor(view!!.context,R.color.blue))
        binding.vouchers.backgroundTintList = ContextCompat.getColorStateList(view!!.context, R.color.white)

        binding.recyclerPrizesVoucher.adapter = prizeAdapter
    }

    private fun onVoucherClicked(){
        binding.prizeText.setTextColor(ContextCompat.getColor(view!!.context, R.color.blue))
        binding.prize.backgroundTintList = ContextCompat.getColorStateList(view!!.context, R.color.white)

        binding.vouchersText.setTextColor(ContextCompat.getColor(view!!.context, R.color.white))
        binding.vouchers.backgroundTintList = ContextCompat.getColorStateList(view!!.context, R.color.blue)

        binding.recyclerPrizesVoucher.layoutManager = LinearLayoutManager(context)

        binding.recyclerPrizesVoucher.adapter = voucherAdapter
    }

}