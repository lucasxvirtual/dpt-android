package com.drogariadopovo.treinamento.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drogariadopovo.domain.model.Question
import com.drogariadopovo.treinamento.databinding.ItemQuizBinding

class QuizAdapter (private var questions: List<Question>): RecyclerView.Adapter<QuizAdapter.Holder>(), AdapterContract {

    lateinit var onButtonClickListener: ((item: Question) -> Unit)

    class Holder(
            private val binding: ItemQuizBinding,
            private val onButtonClickListener: ((item: Question) -> Unit)?) : RecyclerView.ViewHolder(binding.root) {

        lateinit var question: Question

        fun bind(question: Question) {
            this.question = question
            binding.viewHolder = this

            binding.root.setOnClickListener {
                onButtonClickListener?.invoke(question)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
                ItemQuizBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false), onButtonClickListener
        )
    }

    override fun getItemCount(): Int {
        return questions.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(questions[position])
    }

    override fun replaceItems(items: List<*>) {
        this.questions = items.filterIsInstance<Question>() as ArrayList<Question>
        notifyDataSetChanged()
    }
}