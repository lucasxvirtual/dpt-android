package com.drogariadopovo.domain.repository

import com.drogariadopovo.domain.model.Token
import com.drogariadopovo.domain.model.User
import com.drogariadopovo.domain.model.Worked
import io.reactivex.Single

interface AuthRepository{
    fun auth(username : String, password : String) : Single<Token>

    fun getToken() : String?

    fun saveToken(token : String, remember : Boolean = false)

    fun getUser() : Single<User>

    fun saveUser(user: User) : Boolean

    fun saveRemember(boolean: Boolean)

    fun getRemember() : Boolean

//    fun putImage(id : Int, image : String) : Single<User>

    fun putUser(id : Int, name : String, phone : String?) : Single<User>

//    fun putPassword(id: Int, passwordOld: String, passwordNew: String): Single<User>

    fun contact(anonymous : Boolean, message : String) : Single<Worked>

    fun logout()
}