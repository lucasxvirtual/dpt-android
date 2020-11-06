package response

import com.google.gson.annotations.SerializedName

data class VoucherResponse(
        val id : Int,
        val title : String,
        val image : String?,
        @SerializedName("expiration_date") val expirationDate : String?,
        val used : Boolean,
        val user: Int
)