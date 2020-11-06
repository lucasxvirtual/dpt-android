package com.drogariadopovo.domain.usecase

import com.drogariadopovo.domain.WorkedResult
import com.drogariadopovo.domain.repository.AuthRepository
import io.reactivex.Observable
import javax.inject.Inject

class ContactUseCase @Inject constructor(private val authRepository: AuthRepository){


    fun execute(message : String, anonymous : Boolean): Observable<WorkedResult> {
        return authRepository.contact(anonymous, message)
                .toObservable()
                .map {
                    WorkedResult.Success(it) as WorkedResult
                }
                .onErrorReturn { WorkedResult.Failure(it) }
                .startWith(WorkedResult.Loading)
    }
}