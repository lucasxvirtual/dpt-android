package com.drogariadopovo.treinamento.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.drogariadopovo.domain.model.Prize
import com.drogariadopovo.treinamento.R
import com.drogariadopovo.treinamento.databinding.ItemPrizesBinding

class PrizeAdapter(private var prizes: List<Prize>): RecyclerView.Adapter<PrizeAdapter.Holder>(), AdapterContract{

    class Holder(private val binding: ItemPrizesBinding, private val context : Context) : RecyclerView.ViewHolder(binding.root)
    {

        private lateinit var prize: Prize

        fun bind(prize: Prize) {
            this.prize = prize

            binding.itemTitle.text = prize.typeName

            when(prize.level){
                1,2,3,4 -> {
                    binding.trophy.setColorFilter(ContextCompat.getColor(context, R.color.bronze), android.graphics.PorterDuff.Mode.SRC_IN)
                }
                5,6,7,8 -> {
                    binding.trophy.setColorFilter(ContextCompat.getColor(context, R.color.silver), android.graphics.PorterDuff.Mode.SRC_IN)
                }
                else -> {
                    binding.trophy.setColorFilter(ContextCompat.getColor(context, R.color.gold), android.graphics.PorterDuff.Mode.SRC_IN)
                }
            }

            when((prize.level - 1) % 4) {
                0 -> {
                    binding.star2.visibility = View.GONE
                    binding.star3.visibility = View.GONE
                    binding.star4.visibility = View.GONE
                }
                1 -> {
                    binding.star2.visibility = View.VISIBLE
                    binding.star3.visibility = View.GONE
                    binding.star4.visibility = View.GONE
                }
                2 -> {
                    binding.star2.visibility = View.VISIBLE
                    binding.star3.visibility = View.VISIBLE
                    binding.star4.visibility = View.GONE
                }
                3 -> {
                    binding.star2.visibility = View.VISIBLE
                    binding.star3.visibility = View.VISIBLE
                    binding.star4.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
                ItemPrizesBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false),
                parent.context
        )
    }

    override fun getItemCount(): Int {
        return prizes.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(prizes[position])
    }

    override fun replaceItems(items: List<*>) {
        this.prizes = items.filterIsInstance<Prize>() as ArrayList<Prize>
        notifyDataSetChanged()
    }
}