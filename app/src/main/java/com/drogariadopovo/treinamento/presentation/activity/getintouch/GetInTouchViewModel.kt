package com.drogariadopovo.treinamento.presentation.activity.getintouch

import androidx.lifecycle.MutableLiveData
import com.drogariadopovo.domain.WorkedResult
import com.drogariadopovo.domain.usecase.ContactUseCase
import com.drogariadopovo.treinamento.BaseApplication
import com.drogariadopovo.treinamento.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetInTouchViewModel @Inject constructor(private val contactUseCase: ContactUseCase,
                                              private val getInTouchRouter: GetInTouchRouter,
                                              application: BaseApplication) : BaseViewModel(application) {

    val message = MutableLiveData<String>().apply { value = "" }
    val anonymous = MutableLiveData<Boolean>().apply { value = false }

    fun send(){
        val message = this.message.value
        val anonymous = this.anonymous.value

        contactUseCase.execute(message!!, anonymous!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {handleResponse(it)}
                .addTo(disposables)
    }

    private fun handleResponse(result : WorkedResult){
        when(result){
            is WorkedResult.Loading -> {
                showDialog()
            }
            is WorkedResult.Success -> {
                hideDialog()
                setToast(1)
                getInTouchRouter.goBack()
            }
            is WorkedResult.Failure -> {
                hideDialog()
                setToast(2)
            }
        }
    }

}