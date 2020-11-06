package response

import com.drogariadopovo.domain.model.Quiz

class QuizPaginationResponse (
        val count : Int,
        val next : String,
        val previous: String,
        val results : List<QuizResponse>
)