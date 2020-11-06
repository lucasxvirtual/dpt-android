package api

import io.reactivex.Single
import response.*
import retrofit2.http.*

interface AuthenticateEndPoint {

    @FormUrlEncoded
    @POST("core/auth/")
    fun auth(@Field("username") username : String,
             @Field("password") password : String): Single<TokenResponse>

    @GET("core/user/")
    fun getUser() : Single<UserResponse>

    @FormUrlEncoded
    @PATCH("core/user/{id}/")
    fun putImage(@Path("id") id: Int,
                 @Field("profile_image") profile_image : String) : Single<UserResponse>

    @FormUrlEncoded
    @PATCH("core/user/{id}/")
    fun putUser(@Path("id") id: Int,
                @Field("token_notification") token : String) : Single<UserResponse>

    @FormUrlEncoded
    @PATCH("core/user/{id}/")
    fun putUser(@Path ("id") id: Int,
                @Field ("name") name : String,
                @Field ("phone") phone : String?,
                @Field("profile_image") profile_image : String?) : Single<UserResponse>

    @FormUrlEncoded
    @PATCH("core/user/{id}/")
    fun putPassword(@Path ("id") id: Int,
                @Field ("password2") passwordOld : String,
                @Field ("password") passwordNew : String ): Single<UserResponse>

    @FormUrlEncoded
    @POST("core/contact/")
    fun contact(@Field("message") message : String,
                @Field("anonymous") boolean: Boolean) : Single<WorkedResponse>

    @FormUrlEncoded
    @POST("core/user/forgot-password/")
    fun forgotPassword (
        @Field("email") email : String) : Single<WorkedResponse>
}