package com.drogariadopovo.treinamento.presentation.fragment.ranking

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.drogariadopovo.domain.model.Branch
import com.drogariadopovo.domain.model.User
import com.drogariadopovo.domain.usecase.GetRankingBranchUseCase
import com.drogariadopovo.domain.usecase.GetRankingUseCase
import com.drogariadopovo.treinamento.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import storage.Singleton
import javax.inject.Inject

class RankingViewModel @Inject constructor(
        private val router: RankingRouter,
        private val getRanking: GetRankingUseCase,
        private val getRankingBranch: GetRankingBranchUseCase,
        application: Application) : BaseViewModel(application) {

    private val currentUser = MutableLiveData<User>().apply { value = Singleton.instance.getUser()?.blockingGet() }
    private val isStore = MutableLiveData<Boolean>().apply { value = true }
    private val users = MutableLiveData<List<User>>().apply { value = emptyList() }
    private val branches = MutableLiveData<List<Branch>>().apply { value = emptyList() }

    fun getCurrentUser() : LiveData<User> = currentUser
    fun getIsStore() : LiveData<Boolean> = isStore
    fun getUsers() : LiveData<List<User>> = users
    fun getBranches() : LiveData<List<Branch>> = branches


    fun bound(){
        getRankingRequest()
        getRankingBranchesRequest()
    }

    fun onStoreClick(){
        isStore.postValue(true)
    }

    fun onNetClick(){
        isStore.postValue(false)
    }

    private fun getRankingBranchesRequest(){
        getRankingBranch.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {handleGetRankingBranchesResult(it)}
                .addTo(disposables)
    }

    private fun handleGetRankingBranchesResult(result: GetRankingBranchUseCase.Result){
        when (result){
            is GetRankingBranchUseCase.Result.Loading -> {
                showDialog()
            }
            is GetRankingBranchUseCase.Result.Failure -> {
                hideDialog()
            }
            is GetRankingBranchUseCase.Result.Success -> {
                hideDialog()
                branches.postValue(result.branches)
            }
        }
    }

    private fun getRankingRequest(){
        getRanking.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {handleGetRankingResult(it)}
                .addTo(disposables)
    }

    private fun handleGetRankingResult(result: GetRankingUseCase.Result){
        when(result){
            is GetRankingUseCase.Result.Loading -> {
                showDialog()
            }
            is GetRankingUseCase.Result.Failure -> {
                hideDialog()
            }
            is GetRankingUseCase.Result.Success -> {
                hideDialog()
                users.postValue(result.users)
            }
        }
    }

}