package repository

import api.AuthenticateApi
import com.drogariadopovo.domain.model.Token
import com.drogariadopovo.domain.model.User
import com.drogariadopovo.domain.model.Worked
import com.drogariadopovo.domain.repository.AuthRepository
import io.reactivex.Single
import mapper.AuthMapper
import mapper.ContactMapper
import mapper.UserMapper
import mapper.WorkedMapper
import storage.SessionManager
import storage.Singleton
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi : AuthenticateApi,
    private val authMapper: AuthMapper,
    private val userMapper: UserMapper,
    private val workedMapper: WorkedMapper,
    private val contactMapper: ContactMapper,
    private val session : SessionManager
): AuthRepository {

    override fun auth(username : String, password : String): Single<Token> {
        return authApi.auth(username, password).map { authMapper.map((it)) }
    }

    override fun getToken(): String? {
        return session.getToken()
    }

    override fun saveToken(token: String, remember : Boolean) {
        session.saveToken(token)
        saveRemember(remember)
    }

    override fun getUser(): Single<User> {
        return if (Singleton.instance.getUser() != null)
            Singleton.instance.getUser()!!
        else
            authApi.getUser().map { userMapper.map(it) }
    }

    override fun saveUser(user: User) : Boolean {
        Singleton.instance.setUser(user)
        return true
    }

    override fun saveRemember(boolean: Boolean) {
        session.saveRemember(boolean)
    }

    override fun getRemember(): Boolean {
        return session.getRemember()
    }

    override fun putUser(id : Int, name : String, phone : String?, profileImage : String?): Single<User> {
        return authApi.putUser(id, name, phone, profileImage).map { userMapper.map(it) }
    }

    override fun contact(anonymous: Boolean, message: String): Single<Worked> {
        return authApi.contact(anonymous, message).map { workedMapper.map(it) }
    }

//    override fun forgotPassword (email: String): Single<Worked> {
//        return authApi.forgotPassword(email).map { workedMapper.map(it) }
//    }

//    override fun putPassword(id: Int, passwordOld: String, passwordNew: String): Single<User>{
//        return authApi.putPassword(id, passwordOld, passwordNew).map { authMapper.map(it)}
//    }

    override fun logout() {
        session.logout()
    }
}