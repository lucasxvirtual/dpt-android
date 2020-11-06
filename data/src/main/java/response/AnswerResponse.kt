package response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class AnswerResponse (
        val id : Int,
        val text : String,
        @SerializedName("is_right_answer") val isRightAnswer : Boolean?
) : Serializable