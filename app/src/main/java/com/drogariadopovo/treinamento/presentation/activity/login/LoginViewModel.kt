package com.drogariadopovo.treinamento.presentation.activity.login

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.drogariadopovo.domain.UserResult
import com.drogariadopovo.domain.usecase.AuthUseCase
import com.drogariadopovo.treinamento.BaseApplication
import com.drogariadopovo.treinamento.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val authUseCase: AuthUseCase,
                                         private val loginRouter: LoginRouter,
                                         application: BaseApplication) : BaseViewModel(application) {

    val username = MutableLiveData<String>().apply { value = "" }
    private val usernameError = MutableLiveData<Boolean>().apply { value = false }
    val password = MutableLiveData<String>().apply { value = "" }
    private val passwordError = MutableLiveData<Boolean>().apply { value = false }
    val remember = MutableLiveData<Boolean>().apply { value = true }

    fun getUsernameError(): LiveData<Boolean> = usernameError
    fun getPasswordError(): LiveData<Boolean> = passwordError

    fun forgotPassword(){

    }

    fun login(){
        val username = this.username.value
        val password = this.password.value
        val remember = this.remember.value

        if(username.isNullOrEmpty()){
            usernameError.value = true
        }

        if(password.isNullOrEmpty()){
            passwordError.value = true
        }

        if(usernameError.value!! || passwordError.value!!){
            return
        }

        authUseCase.execute(username!!, password!!, remember!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {handleLoginResult(it)}
                .addTo(disposables)

    }

    private fun handleLoginResult(result: UserResult){
        when(result){
            is UserResult.Loading -> {
                showDialog()
            }
            is UserResult.Failure -> {
                hideDialog()
            }
            is UserResult.Success -> {
                hideDialog()
                loginRouter.navigate(LoginRouter.Route.MAIN, Bundle())
            }
        }
    }

}