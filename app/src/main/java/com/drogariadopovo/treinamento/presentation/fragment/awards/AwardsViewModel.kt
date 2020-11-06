package com.drogariadopovo.treinamento.presentation.fragment.awards

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.drogariadopovo.domain.model.Voucher
import com.drogariadopovo.domain.usecase.GetVouchersUseCase
import com.drogariadopovo.treinamento.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AwardsViewModel @Inject constructor(
        private val router: AwardsRouter,
        private val getVouchers: GetVouchersUseCase,
        application: Application) : BaseViewModel(application) {

    private val vouchers = MutableLiveData<List<Voucher>>().apply { value = emptyList() }
    private val isPrizeClicked = MutableLiveData<Boolean>().apply { value = false }

    fun getIsPrizeClicked() : LiveData<Boolean> = isPrizeClicked
    fun getVouchers() : LiveData<List<Voucher>> = vouchers


    fun bound(){
        getVouchersRequest()
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