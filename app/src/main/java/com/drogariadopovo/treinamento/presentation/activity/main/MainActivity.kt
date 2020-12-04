package com.drogariadopovo.treinamento.presentation.activity.main

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import br.com.guiadeacessibilidade.util.SharedPreferencesHelper
import com.drogariadopovo.treinamento.R
import com.drogariadopovo.treinamento.databinding.ActivityMainBinding
import com.drogariadopovo.treinamento.presentation.BaseViewModel
import com.drogariadopovo.treinamento.presentation.activity.BaseActivity
import com.drogariadopovo.treinamento.presentation.activity.login.LoginActivity
import com.drogariadopovo.treinamento.presentation.fragment.profile.ProfileFragment
import com.drogariadopovo.treinamento.presentation.fragment.activities.ActivitiesFragment
import com.drogariadopovo.treinamento.presentation.fragment.awards.AwardsFragment
import com.drogariadopovo.treinamento.presentation.fragment.ranking.RankingFragment
import com.drogariadopovo.treinamento.presentation.popup.QuickQuestionPopup
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    private var isProfile = false
    private val fragment1 = ActivitiesFragment()
    private val fragment2 = AwardsFragment()
    private val fragment3 = RankingFragment()
    private val fragment4 = ProfileFragment()

    override fun getBaseViewModel(): BaseViewModel? {
        return viewModel
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.activity -> {
                addFragment(fragment1)
                supportActionBar?.title = "Atividade"
                invalidateOptionsMenu()
                isProfile = false
                return@OnNavigationItemSelectedListener true
            }
            R.id.awards -> {
                addFragment(fragment2)
                supportActionBar?.title = "Premiações"
                invalidateOptionsMenu()
                isProfile = false
                return@OnNavigationItemSelectedListener true
            }
            R.id.ranking -> {
                addFragment(fragment3)
                supportActionBar?.title = "Ranking"
                invalidateOptionsMenu()
                isProfile = false
                return@OnNavigationItemSelectedListener true
            }
            R.id.profile -> {
                addFragment(fragment4)
                supportActionBar?.title = "Perfil"
                invalidateOptionsMenu()
                isProfile = true
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (isProfile)
            menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun addFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame, fragment, fragment.javaClass.simpleName)
                .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId!! == R.id.exit) {
            val sp = SharedPreferencesHelper(this)
            sp.erase()
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        screenComponent.inject(this)

        viewModel.bound()

        binding.let{
            it.lifecycleOwner = this
            it.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
            it.navigation.isSaveEnabled = false
        }

        viewModel.getQuestion().observe(this, Observer {
            it?.let {question ->
                QuickQuestionPopup(question){ answer ->
                    viewModel.postAnswer(question.id, answer)
                }.show(supportFragmentManager, "")
            }
        })



        supportActionBar?.title = "Atividade"
        addFragment(fragment1)

    }
}