package api

import io.reactivex.Single
import response.ConstantsResponse
import javax.inject.Inject

class ConstantsApi @Inject constructor(private val constantsEndpoint: ConstantsEndpoint){

    fun getConstants() : Single<ConstantsResponse> {
        return constantsEndpoint.getConstants()
    }
}