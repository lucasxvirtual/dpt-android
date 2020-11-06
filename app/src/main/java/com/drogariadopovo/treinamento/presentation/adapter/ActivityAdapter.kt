package com.drogariadopovo.treinamento.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drogariadopovo.domain.model.Quiz
import com.drogariadopovo.treinamento.databinding.ItemActivityBinding

class ActivityAdapter (private var quiz: List<Quiz>): RecyclerView.Adapter<ActivityAdapter.Holder>(), AdapterContract {

    lateinit var onButtonClickListener: ((item: Quiz) -> Unit)

    class Holder(
            private val binding: ItemActivityBinding,
            private val onButtonClickListener: ((item: Quiz) -> Unit)?) : RecyclerView.ViewHolder(binding.root) {

        lateinit var quiz: Quiz

        fun bind(quiz: Quiz) {
            this.quiz = quiz
            binding.viewHolder = this
        }

        fun getFormattedPercent() = String.format("%d%%", (quiz.percent*100).toInt())

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
                ItemActivityBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false), onButtonClickListener
        )
    }

    override fun getItemCount(): Int {
        return quiz.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(quiz[position])

    }

    override fun replaceItems(items: List<*>) {
        this.quiz = items.filterIsInstance<Quiz>() as ArrayList<Quiz>
        notifyDataSetChanged()
    }
}