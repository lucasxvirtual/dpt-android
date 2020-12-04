package com.drogariadopovo.treinamento.presentation.fragment.awards

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.drogariadopovo.domain.PrizeResult
import com.drogariadopovo.domain.model.Prize
import com.drogariadopovo.domain.model.Voucher
import com.drogariadopovo.domain.usecase.GetPrizeUseCase
import com.drogariadopovo.domain.usecase.GetVouchersUseCase
import com.drogariadopovo.treinamento.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AwardsViewModel @Inject constructor(
        private val router: AwardsRouter,
        private val getVouchers: GetVouchersUseCase,
        private val getPrizeUseCase: GetPrizeUseCase,
        application: Application) : BaseViewModel(application) {

    private val vouchers = MutableLiveData<List<Voucher>>().apply { value = emptyList() }
    private val isPrizeClicked = MutableLiveData<Boolean>().apply { value = false }
    private val prizes = MutableLiveData<List<Prize>>().apply { value = emptyList() }

    fun getIsPrizeClicked() : LiveData<Boolean> = isPrizeClicked
    fun getVouchers() : LiveData<List<Voucher>> = vouchers
    fun getPrizes() : LiveData<List<Prize>> = prizes


    fun bound(){
        getVouchersRequest()
        getPrizesRequest()
    }

    fun onPrizeClick(){
        isPrizeClicked.postValue(true)
    }

    fun onVoucherClick(){
        isPrizeClicked.postValue(false)
    }

    private fun getVouchersRequest(){
        getVouchers.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {handleVouchersResult(it)}
                .addTo(disposables)
    }

    private fun getPrizesRequest(){
        getPrizeUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {handlePrizesResult(it)}
                .addTo(disposables)
    }

    private fun handlePrizesResult(result: PrizeResult){
        when (result){
            is PrizeResult.Success -> {
                prizes.postValue(result.prizes)
                hideDialog()
            }
            is PrizeResult.Failure -> {
                hideDialog()
            }
            is PrizeResult.Loading -> {
                showDialog()
            }
        }


    }

    private fun handleVouchersResult(result: GetVouchersUseCase.Result){
        when (result){
            is GetVouchersUseCase.Result.Success -> {
                vouchers.postValue(result.vouchers)
                hideDialog()
            }
            is GetVouchersUseCase.Result.Failure -> {
                hideDialog()
            }
            is GetVouchersUseCase.Result.Loading -> {
                showDialog()
            }
        }


    }

}