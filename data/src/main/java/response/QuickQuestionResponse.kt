package response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class QuickQuestionResponse (val id : Int,
                             @SerializedName("quick_question_answers") val answers : List<AnswerResponse>?,
                             @SerializedName("text") val title : String
) : Serializable