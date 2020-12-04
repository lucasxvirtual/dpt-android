package com.drogariadopovo.treinamento.presentation.fragment.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.guiadeacessibilidade.util.ViewHolder
import com.drogariadopovo.treinamento.R
import com.drogariadopovo.treinamento.databinding.FragmentActivityBinding
import com.drogariadopovo.treinamento.presentation.BaseViewModel
import com.drogariadopovo.treinamento.presentation.activity.quiz.QuizActivity
import com.drogariadopovo.treinamento.presentation.adapter.ActivityAdapter
import com.drogariadopovo.treinamento.presentation.fragment.BaseFragment
import com.drogariadopovo.treinamento.util.GenericRecyclerAdapter
import kotlinx.android.synthetic.main.item_activity.view.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class ActivitiesFragment : BaseFragment() {

    @Inject
    lateinit var viewModel : ActivitiesViewModel
    private lateinit var binding : FragmentActivityBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentActivityBinding.inflate(inflater, container, false)

        screenComponent.inject(this)

        viewModel.bound()

        binding.let {
            it.viewModel = viewModel
            it.lifecycleOwner = this

            it.recycler.layoutManager = LinearLayoutManager(context)
            it.recycler.adapter = ActivityAdapter(emptyList()).apply {
                onButtonClickListener = {viewModel.onClickItem(it)}
            }
//            viewModel.quiz.observe(viewLifecycleOwner, Observer {
//                it?.let {
//                    binding.recycler.adapter =
//                }
//            })
        }

        return binding.root
    }

    override fun getBaseViewModel(): BaseViewModel? {
        return viewModel
    }
}