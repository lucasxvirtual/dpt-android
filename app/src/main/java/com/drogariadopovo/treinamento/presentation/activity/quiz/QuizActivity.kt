package com.drogariadopovo.treinamento.presentation.activity.quiz

import android.os.Bundle
import android.view.MenuItem
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import br.com.guiadeacessibilidade.util.ViewHolder
import com.drogariadopovo.treinamento.R
import com.drogariadopovo.treinamento.databinding.ActivityQuizBinding
import com.drogariadopovo.treinamento.presentation.BaseViewModel
import com.drogariadopovo.treinamento.presentation.activity.BaseActivity
import com.drogariadopovo.treinamento.presentation.activity.duel.DuelActivity
import com.drogariadopovo.treinamento.util.GenericRecyclerAdapter
import kotlinx.android.synthetic.main.item_quiz.view.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class QuizActivity : BaseActivity() {

    var recycler : androidx.recyclerview.widget.RecyclerView? = null
    @Inject
    lateinit var viewModel: QuizViewModel

    override fun getBaseViewModel(): BaseViewModel? {
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityQuizBinding = DataBindingUtil.setContentView(this, R.layout.activity_quiz)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recycler = findViewById(R.id.recycler)
        recycler!!.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        val list = listOf("1","2","3","4")
        setAdapter(list)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    fun setAdapter(list : List<String>){
        recycler?.adapter = GenericRecyclerAdapter<String, ViewHolder>(this, list, object : GenericRecyclerAdapter.GenericRecyclerViewInterface<String, ViewHolder>{
            override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
                return ViewHolder(layoutInflater.inflate(R.layout.item_quiz, parent, false))
            }

            override fun onBindViewHolder(holder: ViewHolder?, position: Int, list: List<String>?) {
                val view = holder!!.itemView
                view.quiz_title.text = "Questionário ${position + 1}"


                if(view.quiz_title.text.contains("4")){
                    view.setOnClickListener {
                        startActivity<DuelActivity>("title" to view.quiz_title.text)
                    }
                }
            }
        })
    }
}
