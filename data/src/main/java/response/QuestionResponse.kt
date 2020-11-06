package response

import com.drogariadopovo.domain.model.Answer
import java.io.Serializable

data class QuestionResponse(val id : Int,
                            val answers : List<AnswerResponse>?,
                            val type : String,
                            val title : String,
                            val description : String?,
                            val image : String?,
                            val video : String?
) : Serializable