package response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    val id : Int,
    @SerializedName("profile_image") val profileImage : String?,
    val email : String,
    val name : String,
    val branch : BranchResponse?,
    val role : RoleResponse?,
    @SerializedName("allow_notification") val allowNotification : Boolean = true,
    val points: Int,
    @SerializedName("last_login") val lastLogin : String?,
    @SerializedName("is_admin") val isAdmin : Boolean,
    @SerializedName("register_date") val registerDate : String,
    @SerializedName("updated_at") val updatedAt : String?,
    @SerializedName("forgot_password_token") val forgotPasswordToken : String?,
    @SerializedName("forgot_password_expiration") val forgotPasswordExpiration : String?,
    val phone: String?
)