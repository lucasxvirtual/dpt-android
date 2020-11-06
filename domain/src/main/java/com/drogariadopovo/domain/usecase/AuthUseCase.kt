package com.drogariadopovo.domain.usecase

import com.drogariadopovo.domain.Helper
import com.drogariadopovo.domain.UserResult
import com.drogariadopovo.domain.repository.AuthRepository
import io.reactivex.Observable
import javax.inject.Inject

class AuthUseCase @Inject constructor(private val authRepository: AuthRepository){


    fun execute(username : String, password : String, remember : Boolean): Observable<UserResult> {
        return authRepository.auth(username, Helper.convertPassMd5(password))
            .flatMap {
                authRepository.saveToken(it.token, remember)
                authRepository.getUser()
            }
            .toObservable()
            .map {
                authRepository.saveUser(it)
                UserResult.Success(it) as UserResult
            }
            .onErrorReturn {UserResult.Failure(it) }
            .startWith(UserResult.Loading)
    }
}