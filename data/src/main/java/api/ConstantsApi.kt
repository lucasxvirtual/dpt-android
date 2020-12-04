package api

import io.reactivex.Single
import okhttp3.ResponseBody
import response.ConstantsResponse
import javax.inject.Inject

class ConstantsApi @Inject constructor(private val constantsEndpoint: ConstantsEndpoint){

    fun getConstants() : Single<ConstantsResponse> {
        return constantsEndpoint.getConstants()
    }

    fun postContact(user : Int?, message: String) : Single<ResponseBody> {
        return constantsEndpoint.postContact(user, message)
    }
}