package com.drogariadopovo.treinamento.presentation.activity.question

import android.app.Activity
import android.os.Bundle
import com.drogariadopovo.treinamento.presentation.BaseRouter
import java.lang.ref.WeakReference

class QuestionRouter(activityRef: WeakReference<Activity>) : BaseRouter(activityRef) {

    enum class Route {
        NEXT
    }

    fun navigate(route: Route, bundle: Bundle = Bundle()) {
        when (route) {
            Route.NEXT -> {
                showNextScreenClearTask(QuestionActivity::class.java, bundle)
            }
        }
    }

}