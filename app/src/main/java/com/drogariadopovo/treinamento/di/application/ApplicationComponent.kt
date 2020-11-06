package com.drogariadopovo.treinamento.di.application

import com.drogariadopovo.treinamento.di.screen.ScreenComponent
import com.drogariadopovo.treinamento.di.screen.ScreenModule
import com.drogariadopovo.treinamento.BaseApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, RepositoryModule::class, EndpointModule::class])
interface ApplicationComponent {

    fun inject(activity: BaseApplication)

    fun plus(screenModule: ScreenModule): ScreenComponent
}
