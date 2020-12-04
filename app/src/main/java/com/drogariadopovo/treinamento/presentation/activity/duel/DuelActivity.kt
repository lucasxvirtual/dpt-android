package com.drogariadopovo.treinamento.presentation.activity.duel

import android.os.Bundle
import android.view.MenuItem
import com.drogariadopovo.treinamento.R
import com.drogariadopovo.treinamento.presentation.BaseViewModel
import com.drogariadopovo.treinamento.presentation.activity.BaseActivity

class DuelActivity : BaseActivity() {

    override fun getBaseViewModel(): BaseViewModel? {
        return null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_duel)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val toolbarTitle = intent.extras!!["title"] as String
        supportActionBar?.title = toolbarTitle
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}