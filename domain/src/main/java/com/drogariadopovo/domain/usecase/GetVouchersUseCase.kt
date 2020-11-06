package com.drogariadopovo.domain.usecase

import com.drogariadopovo.domain.model.Voucher
import com.drogariadopovo.domain.repository.VoucherRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetVouchersUseCase @Inject constructor(private val voucherRepository: VoucherRepository) {

    sealed class Result {
        object Loading : Result()
        data class Success(val vouchers: List<Voucher>) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }

   fun execute() : Observable<Result> {
       return voucherRepository.getVouchers()
               .toObservable()
               .map { Result.Success(it) as Result }
               .onErrorReturn { Result.Failure(it) }
               .startWith(Result.Loading)
   }
}