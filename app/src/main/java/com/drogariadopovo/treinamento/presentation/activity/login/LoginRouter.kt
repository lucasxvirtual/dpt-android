package com.drogariadopovo.treinamento.presentation.activity.login

import android.app.Activity
import android.os.Bundle
import com.drogariadopovo.treinamento.presentation.BaseRouter
import com.drogariadopovo.treinamento.presentation.activity.main.MainActivity
import java.lang.ref.WeakReference

class LoginRouter(activityRef: WeakReference<Activity>) : BaseRouter(activityRef) {

    enum class Route {
        MAIN,
        FORGOT_PASSWORD
    }

    fun navigate(route: Route, bundle: Bundle = Bundle()) {
        when (route) {
            Route.MAIN -> { showNextScreenClearTask(MainActivity::class.java, bundle) }
//            Route.FORGOT_PASSWORD -> { showNextScreen(ForgotPasswordActivity::class.java, bundle)}
        }
    }

}