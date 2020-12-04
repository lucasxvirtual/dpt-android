package com.drogariadopovo.domain.usecase

import com.drogariadopovo.domain.EmptyResult
import com.drogariadopovo.domain.repository.ConstantsRepository
import io.reactivex.Observable
import javax.inject.Inject

class ContactUseCase @Inject constructor(private val constantsRepository: ConstantsRepository){


    fun execute(message : String, user : Int?): Observable<EmptyResult> {
        return constantsRepository.contact(user, message)
                .toObservable()
                .map {
                    EmptyResult.Success(it) as EmptyResult
                }
                .onErrorReturn { EmptyResult.Failure(it) }
                .startWith(EmptyResult.Loading)
    }
}