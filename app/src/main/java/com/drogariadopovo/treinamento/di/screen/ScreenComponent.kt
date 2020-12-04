package com.drogariadopovo.treinamento.di.screen

import com.drogariadopovo.treinamento.di.scope.PerScreen
import com.drogariadopovo.treinamento.presentation.activity.getintouch.GetInTouchActivity
import com.drogariadopovo.treinamento.presentation.activity.login.LoginActivity
import com.drogariadopovo.treinamento.presentation.activity.main.MainActivity
import com.drogariadopovo.treinamento.presentation.activity.question.QuestionActivity
import com.drogariadopovo.treinamento.presentation.activity.quiz.QuizActivity
import com.drogariadopovo.treinamento.presentation.fragment.activities.ActivitiesFragment
import com.drogariadopovo.treinamento.presentation.fragment.awards.AwardsFragment
import com.drogariadopovo.treinamento.presentation.fragment.profile.ProfileFragment
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
    fun inject(quizActivity: QuizActivity)
    fun inject(questionActivity: QuestionActivity)
    fun inject(profileFragment: ProfileFragment)
    fun inject(getInTouchActivity: GetInTouchActivity)

}