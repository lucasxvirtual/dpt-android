package api

import io.reactivex.Single
import response.*
import javax.inject.Inject

class AuthenticateApi @Inject constructor(private val authEndpoint: AuthenticateEndPoint){
    fun auth(username : String, password : String) : Single<TokenResponse> {
        return authEndpoint.auth(username, password)
    }

    fun getUser() : Single<UserResponse>{
        return authEndpoint.getUser()
    }

    fun putUser(id: Int, name : String, phone : String?) : Single<UserResponse>{
        return authEndpoint.putUser(id, name, phone)
    }

    fun putPassword(id: Int, passwordOld: String, passwordNew: String) : Single<UserResponse>{
        return authEndpoint.putPassword(id, passwordOld, passwordNew)
    }

    fun contact(anonymous : Boolean, message : String) : Single<WorkedResponse>{
        return authEndpoint.contact(message, anonymous)
    }

}