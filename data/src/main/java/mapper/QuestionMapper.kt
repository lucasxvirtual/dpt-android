package mapper

import com.drogariadopovo.domain.model.Question
import com.drogariadopovo.domain.model.Quiz
import response.QuestionResponse
import response.QuizResponse
import javax.inject.Inject

class QuestionMapper @Inject constructor(private val answerMapper : AnswerMapper) {

    fun map(response: List<QuestionResponse>) : List<Question> {
        return response.map { map(it) }
    }

    fun map(response: QuestionResponse) : Question {
        return Question(
                id = response.id,
                answers = answerMapper.map(response.answers),
                type = response.type,
                title = response.title,
                description = response.description,
                image = response.image,
                video = response.video
        )
    }

}