package com.drogariadopovo.treinamento.presentation.fragment.activities

import android.app.Activity
import android.os.Bundle
import com.drogariadopovo.treinamento.presentation.BaseRouter
import java.lang.ref.WeakReference

class ActivitiesRouter(activityRef : WeakReference<Activity>) : BaseRouter(activityRef) {

    enum class Route {
        QUIZ
    }

    fun navigate(route: Route, bundle: Bundle = Bundle()) {
        when (route) {
            Route.QUIZ -> {
            }
        }
    }

}