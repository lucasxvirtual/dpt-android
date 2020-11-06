package mapper

import com.drogariadopovo.domain.model.Answer
import response.AnswerResponse
import javax.inject.Inject

class AnswerMapper @Inject constructor(){

    fun map(response: List<AnswerResponse>?) : List<Answer>? {
        if (response != null) {
            return response.map { map(it) }
        }
        return null
    }

    fun map(response : AnswerResponse) : Answer {
        return Answer(
                id = response.id,
                text = response.text,
                isRightAnswer = response.isRightAnswer
        )
    }

}