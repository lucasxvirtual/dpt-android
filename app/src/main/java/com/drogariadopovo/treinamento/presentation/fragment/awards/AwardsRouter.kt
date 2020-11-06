package com.drogariadopovo.treinamento.presentation.fragment.awards

import android.app.Activity
import android.os.Bundle
import com.drogariadopovo.treinamento.presentation.BaseRouter
import java.lang.ref.WeakReference

class AwardsRouter(private val activityRef: WeakReference<Activity>) : BaseRouter(activityRef) {

    enum class Route {

    }

    fun navigate(route: Route, bundle: Bundle = Bundle()){
        when (route){

        }
    }
}