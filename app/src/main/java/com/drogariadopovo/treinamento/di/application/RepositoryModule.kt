package com.drogariadopovo.treinamento.di.application

import api.*
import com.drogariadopovo.domain.repository.AuthRepository
import com.drogariadopovo.domain.repository.QuizRepository
import com.drogariadopovo.domain.repository.RankingRepository
import com.drogariadopovo.domain.repository.VoucherRepository
import dagger.Module
import dagger.Provides
import mapper.*
import repository.*
import storage.SessionManager
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(authApi: AuthenticateApi, authMapper: AuthMapper, userMapper: UserMapper, workedMapper: WorkedMapper, contactMapper: ContactMapper, sessionManager: SessionManager) : AuthRepository {
        return AuthRepositoryImpl(authApi, authMapper, userMapper, workedMapper, contactMapper, sessionManager)
    }

    @Provides
    @Singleton
    fun providesRankingRepository(rankingApi: RankingApi, branchMapper: BranchMapper, userMapper: UserMapper) : RankingRepository {
        return RankingRepositoryImpl(rankingApi, branchMapper, userMapper)
    }

    @Provides
    @Singleton
    fun providesVoucherRepository(voucherApi: VoucherApi, voucherMapper: VoucherMapper) : VoucherRepository {
        return VoucherRepositoryImpl(voucherApi, voucherMapper)
    }

    @Provides
    @Singleton
    fun providesQuizRepository(quizApi: QuizApi, quizPaginationMapper: QuizPaginationMapper, userAnswerMapper: UserAnswerMapper, userMapper: UserMapper, branchMapper: BranchMapper, voucherMapper: VoucherMapper) : QuizRepository {
        return QuizRepositoryImpl(quizApi, quizPaginationMapper, userAnswerMapper, userMapper, branchMapper, voucherMapper)
    }
}