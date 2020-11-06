package response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class QuizResponse(
        val id: Int,
        val questions : ArrayList<QuestionResponse>,
        val percent : Float = 0f,
        val title : String,
        val description : String?,
        @SerializedName("due_date") val dueDate : String?
) : Serializable