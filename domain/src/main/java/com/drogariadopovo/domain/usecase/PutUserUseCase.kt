package com.drogariadopovo.domain.usecase

import com.drogariadopovo.domain.UserResult
import com.drogariadopovo.domain.repository.AuthRepository
import io.reactivex.Observable
import javax.inject.Inject

class PutUserUseCase @Inject constructor(private val authRepository: AuthRepository) {

    fun execute(id : Int, name : String, phone : String) : Observable<UserResult> {

        return authRepository.putUser(id, name, phone)
                .toObservable()
                .map {
                    authRepository.saveUser(it)
                    UserResult.Success(it) as UserResult
                }
                .onErrorReturn {UserResult.Failure(it) }
                .startWith(UserResult.Loading)

    }

}