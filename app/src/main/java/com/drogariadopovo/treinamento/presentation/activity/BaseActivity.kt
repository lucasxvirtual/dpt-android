package com.drogariadopovo.treinamento.presentation.activity

import android.app.Dialog
import android.view.View
import android.view.Window
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.drogariadopovo.treinamento.BaseApplication
import com.drogariadopovo.treinamento.R
import com.drogariadopovo.treinamento.di.application.ApplicationComponent
import com.drogariadopovo.treinamento.di.screen.ScreenModule
import com.drogariadopovo.treinamento.presentation.BaseViewModel
import org.jetbrains.anko.toast

abstract class BaseActivity : AppCompatActivity() {

    val screenComponent by lazy {
        getApplicationComponent().plus(ScreenModule(this))
    }

    private fun getApplicationComponent(): ApplicationComponent {
        return (application as BaseApplication).component
    }

    private var dialogTransparent : Dialog? = null

    override fun onStart() {
        super.onStart()

        dialogTransparent = Dialog(this, R.style.Theme_AppCompat_Light )
        val view = View.inflate(
                this, R.layout.progress, null )
        dialogTransparent?.requestWindowFeature( Window.FEATURE_NO_TITLE )
        dialogTransparent?.window?.setBackgroundDrawableResource( R.color.transparent )
        dialogTransparent?.setContentView( view )

        getBaseViewModel()?.getLoadingVisibility()?.observe(this,
                Observer {
                    it.let { if(it) showDialog() else hideDialog() }
                })

        getBaseViewModel()?.getToast()?.observe(this,
                Observer {
                    val message = getMessage(it)
                    if(message != null)
                        toast(message)
                })
    }

    open fun getMessage(number: Int?): String?{
        return null
    }

    abstract fun getBaseViewModel(): BaseViewModel?

    fun setupError(editText: EditText,
                   liveData: LiveData<Boolean>,
                   error: String = getString(R.string.required_field)){
        liveData.observe(this, Observer {
            it?.let {
                if(it) editText.error = error
            }
        })
    }

    /**
     * show the wait dialog
     */
    private fun showDialog( ){
        dialogTransparent?.show( )
    }

    /**
     * hide the wait dialog
     */
    private fun hideDialog( ){
        dialogTransparent?.dismiss( )
    }

}