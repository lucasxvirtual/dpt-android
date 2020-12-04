package com.drogariadopovo.domain.usecase

import com.drogariadopovo.domain.UserResult
import com.drogariadopovo.domain.repository.AuthRepository
import io.reactivex.Observable
import javax.inject.Inject

class RememberUseCase @Inject constructor(private val authRepository: AuthRepository){

    fun execute(): Observable<UserResult>? {

        if(authRepository.getRemember() && authRepository.getToken() != null){
            return authRepository.getUser().toObservable()
                    .map {
                        authRepository.saveUser(it)
                        UserResult.Success(it) as UserResult
                    }
                    .onErrorReturn { UserResult.Failure(it) }
                    .startWith(UserResult.Loading)
        }
        return null
    }
}