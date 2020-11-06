package com.drogariadopovo.treinamento.di.application

import api.*
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class EndpointModule {

    @Provides
    @Singleton
    fun provideAuthEndpoint(retrofit: Retrofit) : AuthenticateEndPoint{
        return retrofit.create(AuthenticateEndPoint::class.java)
    }

    @Provides
    @Singleton
    fun provideConstantsEndpoint(retrofit: Retrofit) : ConstantsEndpoint{
        return retrofit.create(ConstantsEndpoint::class.java)
    }

    @Provides
    @Singleton
    fun providesRankingEndpoint(retrofit: Retrofit) : RankingEndPoint{
        return retrofit.create(RankingEndPoint::class.java)
    }

    @Provides
    @Singleton
    fun providesVoucherEndpoint(retrofit: Retrofit) : VoucherEndPoint{
        return retrofit.create(VoucherEndPoint::class.java)
    }

    @Provides
    @Singleton
    fun providesQuizEndpoint(retrofit: Retrofit) : QuizEndPoint{
        return retrofit.create(QuizEndPoint::class.java)
    }

}