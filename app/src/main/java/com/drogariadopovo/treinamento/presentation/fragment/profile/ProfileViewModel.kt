package com.drogariadopovo.treinamento.presentation.fragment.profile

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.drogariadopovo.domain.UserResult
import com.drogariadopovo.domain.model.User
import com.drogariadopovo.domain.usecase.PutUserUseCase
import com.drogariadopovo.treinamento.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import storage.Singleton
import javax.inject.Inject

class ProfileViewModel @Inject constructor(application: Application,
                                           private val putUserUseCase: PutUserUseCase
                                           ) : BaseViewModel(application) {

    val user = MutableLiveData<User>().apply { value = Singleton.instance.getUser()?.blockingGet() }

    fun getUser() = user.value!!

    fun saveUser(name : String, phone : String){
        putUserUseCase.execute(user.value!!.id, name, phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {handleResult(it)}
                .addTo(disposables)
    }

    private fun handleResult(result: UserResult){
        when(result){
            is UserResult.Loading -> {
                showDialog()
            }
            is UserResult.Failure -> {
                hideDialog()
            }
            is UserResult.Success -> {
                user.postValue(result.user)
                hideDialog()
            }
        }
    }

}