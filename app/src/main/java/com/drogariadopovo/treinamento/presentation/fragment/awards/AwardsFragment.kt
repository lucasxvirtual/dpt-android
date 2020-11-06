package com.drogariadopovo.treinamento.presentation.fragment.awards

import android.os.Bundle
import androidx.core.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.guiadeacessibilidade.util.ViewHolder
import com.drogariadopovo.treinamento.R
import com.drogariadopovo.treinamento.databinding.FragmentAwardsBinding
import com.drogariadopovo.treinamento.presentation.BaseViewModel
import com.drogariadopovo.treinamento.presentation.adapter.VoucherAdapter
import com.drogariadopovo.treinamento.presentation.fragment.BaseFragment
import com.drogariadopovo.treinamento.util.GenericRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_awards.*
import kotlinx.android.synthetic.main.fragment_awards.view.*
import javax.inject.Inject

class AwardsFragment : BaseFragment() {

    private var list = listOf("1","2","3")

    @Inject
    lateinit var viewModel : AwardsViewModel

    lateinit var binding: FragmentAwardsBinding

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
                binding.recyclerPrizesVoucher.adapter = VoucherAdapter(it, this).apply {
                    onButtonClickListener = {  }
                }
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

        setAdapterPrizes(list)
    }

    private fun onVoucherClicked(){
        binding.prizeText.setTextColor(ContextCompat.getColor(view!!.context, R.color.blue))
        binding.prize.backgroundTintList = ContextCompat.getColorStateList(view!!.context, R.color.white)

        binding.vouchersText.setTextColor(ContextCompat.getColor(view!!.context, R.color.white))
        binding.vouchers.backgroundTintList = ContextCompat.getColorStateList(view!!.context, R.color.blue)

        binding.recyclerPrizesVoucher.layoutManager = LinearLayoutManager(context)
        binding.recyclerPrizesVoucher.adapter = VoucherAdapter(viewModel.getVouchers().value!!, this).apply {
            onButtonClickListener = {  }
        }
    }

    //TODO: Created adapter mvvm
    fun setAdapterPrizes(list : List<String>){
        recycler_prizes_voucher?.adapter = GenericRecyclerAdapter<String, ViewHolder>(context, list, object : GenericRecyclerAdapter.GenericRecyclerViewInterface<String, ViewHolder>{
            override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
                return ViewHolder(layoutInflater.inflate(R.layout.item_prizes, parent, false))
            }

            override fun onBindViewHolder(holder: ViewHolder?, position: Int, list: List<String>?) {
                val view = holder!!.itemView

            }
        })
    }

}