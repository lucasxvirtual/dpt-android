package mapper

import com.drogariadopovo.domain.model.Contact
import com.drogariadopovo.domain.model.Quiz
import response.ContactResponse
import response.QuizResponse
import javax.inject.Inject

class QuizMapper @Inject constructor(private val questionMapper: QuestionMapper){

    fun map(response: List<QuizResponse>) : List<Quiz> {
        return response.map { map(it) }
    }

    fun map(response : QuizResponse) : Quiz {
        return Quiz (
                id = response.id,
                questions = questionMapper.map(response.questions),
                percent = response.percent,
                title = response.title,
                description = response.description,
                dueDate = response.dueDate
        )
    }
}