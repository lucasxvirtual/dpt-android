package storage

import android.app.Application
import javax.inject.Inject

class SessionManager @Inject constructor(application: Application): SharedPreferencesHelper(application) {

    companion object {
        const val TOKEN = "token"
        const val KEEP_LOGGED = "keep_logged"
        const val CLIENT = "client"
    }

    fun saveToken(token: String){
        putString(TOKEN, token)
        //putString(KEEP_LOGGED, keepLogged.toString())
    }

    fun saveRemember(keepLogged: Boolean = false){
        putString(KEEP_LOGGED, keepLogged.toString())
    }

    fun getRemember() : Boolean{
        return if (getString(KEEP_LOGGED) != null)
            getString(KEEP_LOGGED)!!.toBoolean()
        else false
    }

    fun getToken() : String?{
        return if (getString(TOKEN) != null)
            getString(TOKEN)
        else ""
    }

    fun logout() = erase()
}