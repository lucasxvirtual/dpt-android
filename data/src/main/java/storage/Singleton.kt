package storage

import com.drogariadopovo.domain.model.Constants
import com.drogariadopovo.domain.model.User
import io.reactivex.Single

class Singleton private constructor(){

    private object HOLDER {
        val INSTANCE = Singleton()
    }

    companion object{
        val instance  : Singleton by lazy {  HOLDER.INSTANCE }
        private var user : Single<User>? = null
        private var constants : Single<Constants>? = null
    }

    fun setUser(user: User){
        Companion.user = Single.just(user)
    }

    fun getUser() = user

    fun clearUser(){
        user = null
    }

    fun setConstants(constants: Constants){
        Companion.constants = Single.just(constants)
    }

    fun getConstants() = constants
}