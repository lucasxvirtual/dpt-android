package com.drogariadopovo.treinamento.di.screen

import com.drogariadopovo.treinamento.di.scope.PerScreen
import com.drogariadopovo.treinamento.presentation.activity.login.LoginActivity
import com.drogariadopovo.treinamento.presentation.activity.main.MainActivity
import com.drogariadopovo.treinamento.presentation.fragment.activities.ActivitiesFragment
import com.drogariadopovo.treinamento.presentation.fragment.awards.AwardsFragment
import com.drogariadopovo.treinamento.presentation.fragment.ranking.RankingFragment
import dagger.Subcomponent

@PerScreen
@Subcomponent(modules = [ScreenModule::class])
interface ScreenComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(loginActivity: LoginActivity)
    fun inject(rankingFragment: RankingFragment)
    fun inject(awardsFragment: AwardsFragment)
    fun inject(activitiesFragment: ActivitiesFragment)

}