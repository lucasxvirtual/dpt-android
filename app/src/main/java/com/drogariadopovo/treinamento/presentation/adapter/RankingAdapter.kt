package com.drogariadopovo.treinamento.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drogariadopovo.domain.model.Branch
import com.drogariadopovo.domain.model.Ranking
import com.drogariadopovo.domain.model.User
import com.drogariadopovo.treinamento.databinding.ItemStoreBinding

class RankingAdapter(private var ranking: List<Ranking>?): RecyclerView.Adapter<RankingAdapter.Holder>(), AdapterContract{

    lateinit var onItemClickListener: ((item: Ranking) -> Unit)

    class Holder(
            private val binding: ItemStoreBinding,
            private val onItemClickListener: ((item: Ranking) -> Unit)?) : RecyclerView.ViewHolder(binding.root)
    {

        private lateinit var ranking: Ranking

        fun bind(ranking: Ranking, position: Int) {
            this.ranking = ranking
            binding.viewHolder = this

            binding.rankingNumber.text = position.toString()
            binding.name.text = ranking.name
            binding.points.text = ranking.points

            if (!ranking.highlight){
                binding.border.visibility = View.GONE
            } else {
                binding.border.visibility = View.VISIBLE
            }

            binding.root.setOnClickListener { onItemClickListener?.invoke(ranking) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemStoreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false), onItemClickListener
        )
    }

    override fun getItemCount(): Int {
        return if (ranking.isNullOrEmpty()){
            0
        } else ranking!!.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(ranking!![position], position + 1)

    }

    override fun replaceItems(items: List<*>) {
        this.ranking = items.filterIsInstance<Ranking>() as ArrayList<Ranking>
        notifyDataSetChanged()
    }

    fun userToRanking(users: List<User>, currentUser: User) {
        val map: List<Ranking> = users.map { Ranking(it.name, it.points.toString(), it == currentUser) }
        ranking = map
        notifyDataSetChanged()
    }

    fun branchToRanking(branches: List<Branch>, currentUser: User) {
        val map: List<Ranking> = branches.map { Ranking(it.name, it.points.toString(), it == currentUser.branch) }
        ranking = map
        notifyDataSetChanged()
    }
}