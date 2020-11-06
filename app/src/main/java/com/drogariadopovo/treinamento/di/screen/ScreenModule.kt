package com.drogariadopovo.treinamento.di.screen

import com.drogariadopovo.treinamento.presentation.activity.BaseActivity
import com.drogariadopovo.treinamento.di.scope.PerScreen
import com.drogariadopovo.treinamento.presentation.activity.login.LoginRouter
import com.drogariadopovo.treinamento.presentation.fragment.activities.ActivitiesRouter
import com.drogariadopovo.treinamento.presentation.fragment.awards.AwardsRouter
import com.drogariadopovo.treinamento.presentation.fragment.ranking.RankingRouter

import dagger.Module
import dagger.Provides
import java.lang.ref.WeakReference

@Module
class ScreenModule(private val activity: BaseActivity) {

    //    @PerScreen
    //    @Provides
    //    fun getApplication() : Application{
    //        return application
    //    }

    @PerScreen
    @Provides
    fun providesActivity(): BaseActivity {
        return activity
    }

    @PerScreen
    @Provides
    fun providesLoginRouter(): LoginRouter {
        return LoginRouter(WeakReference(activity))
    }

    @PerScreen
    @Provides
    fun providesRankingRouter(): RankingRouter {
        return RankingRouter(WeakReference(activity))
    }

    @PerScreen
    @Provides
    fun providesAwardsRouter() : AwardsRouter {
        return AwardsRouter(WeakReference(activity))
    }

    @PerScreen
    @Provides
    fun providesActivitiesRouter() : ActivitiesRouter {
        return ActivitiesRouter(WeakReference(activity))
    }
}