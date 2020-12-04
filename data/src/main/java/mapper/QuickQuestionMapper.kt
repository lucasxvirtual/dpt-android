package mapper

import com.drogariadopovo.domain.model.Question
import response.QuickQuestionResponse
import javax.inject.Inject

class QuickQuestionMapper @Inject constructor(private val answerMapper : AnswerMapper) {

    fun map(response: List<QuickQuestionResponse>) : List<Question> {
        return response.map { map(it) }
    }

    fun map(response: QuickQuestionResponse) : Question {
        return Question(
                id = response.id,
                answers = answerMapper.map(response.answers),
                type = "QUICK_QUESTION",
                title = response.title,
                description = "",
                image = "",
                video = ""
        )
    }

}