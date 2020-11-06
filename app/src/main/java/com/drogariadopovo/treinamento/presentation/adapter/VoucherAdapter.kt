package com.drogariadopovo.treinamento.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drogariadopovo.domain.model.Voucher
import com.drogariadopovo.treinamento.R
import com.drogariadopovo.treinamento.databinding.ItemVouchersBinding
import com.drogariadopovo.treinamento.presentation.fragment.BaseFragment
import com.drogariadopovo.treinamento.util.Helper
import com.squareup.picasso.Picasso

class VoucherAdapter(private var vouchers: List<Voucher>, private val fragment: BaseFragment): RecyclerView.Adapter<VoucherAdapter.Holder>(), AdapterContract{

    lateinit var onButtonClickListener: ((item: Voucher) -> Unit)

    class Holder(
            private val binding: ItemVouchersBinding,
            private val fragment: BaseFragment,
            private val onButtonClickListener: ((item: Voucher) -> Unit)?) : RecyclerView.ViewHolder(binding.root)
    {

        private lateinit var voucher: Voucher

        fun bind(voucher: Voucher) {
            this.voucher = voucher
            binding.viewHolder = this

            binding.voucherTitle.text = voucher.title
            if (!voucher.expirationDate.isNullOrEmpty()){
                binding.expiringDate.text = String.format(fragment.getString(R.string.expiring_date), Helper.convertDateForApp(voucher.expirationDate!!))
            }


            if (!voucher.image.isNullOrEmpty()){
                Picasso.get()
                        .load(voucher.image)
                        .fit()
                        .centerCrop()
                        .into(binding.image)
            }


            binding.expandIcon.setOnClickListener { onButtonClickListener?.invoke(voucher) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
                ItemVouchersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false), fragment, onButtonClickListener
        )
    }

    override fun getItemCount(): Int {
        return vouchers.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(vouchers[position])

    }

    override fun replaceItems(items: List<*>) {
        this.vouchers = items.filterIsInstance<Voucher>() as ArrayList<Voucher>
        notifyDataSetChanged()
    }

}