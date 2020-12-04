package response

import com.google.gson.annotations.SerializedName

data class PrizeResponse(@SerializedName("type_name") val typeName : String, val level : Int)