package com.drogariadopovo.treinamento.di.application

import api.*
import com.drogariadopovo.domain.repository.*
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
    fun provideConstantsRepository(constantsApi: ConstantsApi, constantsMapper: ConstantsMapper) : ConstantsRepository {
        return ConstantsRepositoryImpl(constantsApi, constantsMapper)
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
    fun providesQuizRepository(quizApi: QuizApi, quizPaginationMapper: QuizPaginationMapper, userAnswerMapper: UserAnswerMapper, userMapper: UserMapper, branchMapper: BranchMapper, voucherMapper: VoucherMapper, quickQuestionMapper: QuickQuestionMapper, workedMapper: WorkedMapper, prizeMapper: PrizeMapper) : QuizRepository {
        return QuizRepositoryImpl(quizApi, quizPaginationMapper, userAnswerMapper, userMapper, branchMapper, voucherMapper, quickQuestionMapper, workedMapper, prizeMapper)
    }
}