package api

import io.reactivex.Single
import okhttp3.ResponseBody
import response.ConstantsResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ConstantsEndpoint {

    @GET("core/constants/")
    fun getConstants() : Single<ConstantsResponse>

    @FormUrlEncoded
    @POST("core/contact/")
    fun postContact(
            @Field("user") user : Int?,
            @Field("message") message : String
    ) : Single<ResponseBody>

}