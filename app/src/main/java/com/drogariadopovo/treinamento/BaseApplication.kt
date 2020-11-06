package com.drogariadopovo.treinamento

import androidx.multidex.MultiDexApplication
import com.drogariadopovo.treinamento.di.application.ApplicationModule
import com.drogariadopovo.treinamento.di.application.ApplicationComponent
import com.drogariadopovo.treinamento.di.application.DaggerApplicationComponent

class BaseApplication : MultiDexApplication() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        inject()
    }

    fun inject() {
        component = DaggerApplicationComponent.builder().applicationModule(
                ApplicationModule(this)
        ).build()
        component.inject(this )
    }

}