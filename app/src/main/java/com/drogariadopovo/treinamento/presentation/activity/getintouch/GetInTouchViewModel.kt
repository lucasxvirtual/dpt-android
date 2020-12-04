package com.drogariadopovo.treinamento.presentation.activity.getintouch

import androidx.lifecycle.MutableLiveData
import com.drogariadopovo.domain.EmptyResult
import com.drogariadopovo.domain.usecase.ContactUseCase
import com.drogariadopovo.treinamento.BaseApplication
import com.drogariadopovo.treinamento.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import storage.Singleton
import javax.inject.Inject

class GetInTouchViewModel @Inject constructor(private val contactUseCase: ContactUseCase,
                                              private val getInTouchRouter: GetInTouchRouter,
                                              application: BaseApplication) : BaseViewModel(application) {

    val message = MutableLiveData<String>().apply { value = "" }
    val anonymous = MutableLiveData<Boolean>().apply { value = false }
    private val user = Singleton.instance.getUser()?.blockingGet()

    fun send(){
        val message = this.message.value
        val id = if(anonymous.value!!) user?.id else null

        contactUseCase.execute(message!!, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { handleResponse(it) }
                .addTo(disposables)
    }

    private fun handleResponse(result : EmptyResult){
        when(result){
            is EmptyResult.Loading -> {
                showDialog()
            }
            is EmptyResult.Success -> {
                hideDialog()
                setToast(1)
                getInTouchRouter.goBack()
            }
            is EmptyResult.Failure -> {
                hideDialog()
                setToast(2)
            }
        }
    }

}